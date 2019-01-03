package com.huanghe.lbsn.Service.impl;

import com.huanghe.lbsn.Entity.Check;
import com.huanghe.lbsn.Entity.Poi;
import com.huanghe.lbsn.Entity.User;
import com.huanghe.lbsn.Mapper.CheckMapper;
import com.huanghe.lbsn.Mapper.PoiMapper;
import com.huanghe.lbsn.Mapper.RelationsMapper;
import com.huanghe.lbsn.Service.RecommandService;
import com.huanghe.lbsn.utils.LocationsUtils;
import com.huanghe.lbsn.utils.NormailzeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Author huanghe
 * @Date 2018/12/29 11:51
 * @Description
 */
@Service
public class RecommandServiceImpl implements RecommandService {

    @Autowired
    private CheckMapper checkMapper;

    @Autowired
    private PoiMapper poiMapper;

    @Autowired
    private RelationsMapper relationsMapper;


    @Override
    public List<Poi> recommand(HttpServletRequest request , String token, String latitude, String longitude) {
        List<Poi> poiList = new ArrayList<>();
        //如果当前用户未登陆或者是当前用户没有任何的行为记录
        if (token == null) {
            List<Integer> poiIds = recommandNoUser(latitude, longitude);
            for (Integer poiId : poiIds) {
                Poi poi = poiMapper.selectByPrimaryKey(poiId);
                poiList.add(poi);
            }
            return poiList;
        }
        //获取当前用户的签到行为记录
        List<Integer> poiIds = recommandBaseUser(request,token, latitude, longitude);
        for (Integer poiId : poiIds) {
            Poi poi = poiMapper.selectByPrimaryKey(poiId);
            poiList.add(poi);
        }
        return poiList;

    }


    /**
     * 基于用户的协同过滤推荐算法,用户已经登陆
     * @param token：用户的token指令
     * @param latitude：维度
     * @param longitude：经度
     * @return
     */
    public  List<Integer> recommandBaseUser(HttpServletRequest request,String token,String latitude,String longitude) {
        List<Integer> poiIds = new ArrayList<>();
        //获取当前用户的签到行为
        User user = (User)request.getSession().getAttribute(token);
        Integer currentUserId = user.getUserid();
        List<Check> checks = checkMapper.selectCheckByUserId(currentUserId);
        //如果用户没有签到行为，按照冷启动推荐
        if (checks.size() == 0) {
            poiIds = recommandNoUser(latitude, longitude);
            return poiIds;
        }
        //如果用户有签到行为按照 USG算法进行推荐
        poiIds = USG(currentUserId, latitude, longitude);
        return poiIds;
    }

    /**
     * USG算法
     *
     * @param currentUserID ：当前用户ID
     * @param latitude      :维度
     * @param longitude     ：经度
     * @return
     */
    public List<Integer> USG(int currentUserID, String latitude, String longitude) {

        //得到签到矩阵 user数目 * poi数目
        Set<String> userSet = new LinkedHashSet();
        Set<String> poiSet = new LinkedHashSet();
        List<Map<String, Object>> checkIn = checkMapper.getCheckIn();
        for (Map<String, Object> map : checkIn) {
            userSet.add(map.get("userId").toString());
            poiSet.add(map.get("poiId").toString());
        }
        //用户数
        Integer USER_NUM = userSet.size();
        //POI的位置数
        Integer POI_NUM = poiSet.size();
        //用户-POI-签到次数
        List<Map<String,Object>> user_check_count = checkMapper.getUserCheckCount();

        //三、得到好友关系数据(key是存放的是用户的id,value值是这个用户的好友)
        HashMap<Integer, Set<String>> relations = new HashMap<>();
        List<Map<String, Object>> allRelations = relationsMapper.findAllRelations();
        for (Map<String, Object> map : allRelations) {
            Integer userId = (Integer) map.get("user_id");
            Set<String> friends_set = new HashSet<String>(Arrays.asList(map.get("friend_set").toString().split(",")));
            relations.put(userId, friends_set);
        }
        //基于用户的协同过滤推荐算法，计算出当前用户推荐id的得分值
        List<Integer> UCF_recommand = recommand(userSet,poiSet, user_check_count, currentUserID,latitude,longitude,relations);
        return UCF_recommand;
    }

    /**
     * 推荐地点位置，用户未登陆
     * @param latitude：纬度
     * @param longitude：经度
     * @return 推荐地点id的集合
     */
    public  List<Integer> recommandNoUser(String latitude,String longitude) {
        List<Integer> poiList = new ArrayList<>();
        //1、获取poi平均得分列表,从高到低的排序，默认获取20个
        List<Map<Integer, Object>> poiAvgScore = checkMapper.getPoiAvgScore();
        for (Map<Integer, Object> map : poiAvgScore) {
            poiList.add(Integer.parseInt(map.get("poi_id").toString()));
        }
        //2、从获取的地点计算距离的
        HashMap<Integer,Double> distanceMap = new HashMap<>();
        for (Integer poiId : poiList) {
            Poi poi = poiMapper.selectByPrimaryKey(poiId);
            distanceMap.put(poiId,LocationsUtils.getDistance(Double.valueOf(latitude), Double.valueOf(longitude), Double.valueOf(poi.getLatitude()), Double.valueOf(poi.getLongitude())));
        }
        //3、获取每个poi的签到的次数
        HashMap<Integer,Double> countMap = new HashMap<>();
        for (Map<Integer, Object> map : poiAvgScore) {
            countMap.put(Integer.parseInt(map.get("poi_id").toString()),Double.valueOf(map.get("poiCount").toString()));
        }
        //4、获取每个位置签到的平均得分值
        HashMap<Integer,Double> poiScoreMap = new HashMap<>();
        for (Map<Integer, Object> map : poiAvgScore) {
            poiScoreMap.put(Integer.parseInt(map.get("poi_id").toString()),Double.valueOf(map.get("avgScore").toString()));
        }
        //5、数据归一化处理
        HashMap<Integer,Double> DistanceScore =new NormailzeUtils().normailzeDistance(distanceMap);
        HashMap<Integer,Double> CountScore = new NormailzeUtils().normailze(countMap);
        HashMap<Integer,Double> AvgScore = new NormailzeUtils().normailze(poiScoreMap);

        //6、计算最后的得分值 score = αCountScore + βDistanceScore +(1-α-β)AvgScore ,取α=0.5 取β=0.3
        LinkedHashMap<Integer, Double> FianlScore = new LinkedHashMap<>();
        double a = 0.5, b = 0.3;
        for (Integer key : CountScore.keySet()) {
            if (DistanceScore.containsKey(key) && AvgScore.containsKey(key)) {
                FianlScore.put(key, a * CountScore.get(key) + b * DistanceScore.get(key) + (1 - a - b) * AvgScore.get(key));
            }
        }
        //7、对最后的得分进行排序,从大到小
        List<Map.Entry<Integer, Double>> list = new ArrayList<>(FianlScore.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Double>>() {
            @Override
            public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2) {
                return (int)(o2.getValue() - o1.getValue());
            }
        });
        //8、取出推荐前10的id
        int count = 0;
        List<Integer> recommandPoi = new ArrayList<>();
        for(Map.Entry<Integer, Double> t:list){
            if (count++ >= 10) {
                break;
            }
            recommandPoi.add(t.getKey());
        }
        return recommandPoi;
    }

    /**
     *
     * @param poiSet:poi地点集合
     * @param user_check_count：用户签到的次数
     * @param currentUserID:当前用户id
     * @return
     */
    public  List<Integer> recommand(Set<String> userSet,Set<String> poiSet,List<Map<String,Object>> user_check_count,
                                    Integer currentUserID,String latitude,String longitude,HashMap<Integer, Set<String>> relations) {
        //一、基于用户的协同过滤
        //1、当前用户签到集合，所有用户的签到集合，所有用户的签到次数
        Set<String> current_user_poi = new LinkedHashSet<>();
        //用户签到集合Map，key为用户ID,值为用户访问的POI
        Map<Integer, Set<String>> user_poi = new HashMap<>();
        //用户-poi-打分Map,key为用户，值为map（map里面存放的是这个用户访问的POI:count)
        Map<Integer, Map<String, String>> user_poi_score = new HashMap<>();
        //初始化user_poi_score,把所有的用户在所有的地点签到的次数初始化为0
        for (String user : userSet) {
            HashMap<String, String> map = new HashMap<>();
            for (String poi : poiSet) {
                map.put(poi, "0");
            }
            user_poi_score.put(Integer.valueOf(user), map);
        }

        for (Map<String, Object> map : user_check_count) {
            //获取当前的用户id
            Integer UserId = (Integer) map.get("user_id");
            //当前用户签到的POI集合
            Set<String> poi_set = new LinkedHashSet<>(Arrays.asList(map.get("poi_id").toString().split(",")));
            //用户的访问次数集合
            List<String> count_set = (Arrays.asList(map.get("count").toString().split(",")));

            //获取目标用户的签到集合
            if (UserId.equals(currentUserID)) {
                current_user_poi = poi_set;
            }
            //所有用户签到集合Map，key为用户id,value为用户签到集合value
            user_poi.put(UserId, poi_set);
            //所有的用户签到分数集合的Map，key为用户id，value为Map(Map中的key为poiId,value为count)
            Map<String, String> poi_score_map = new HashMap<>();
            int i = 0;
            //遍历的是所有的地点
            for (String str : poiSet) {
                //如果当前位置的地点有得分值则加入得分值，如果没有得分值则为0
                if (poi_set.contains(str)) {
                    poi_score_map.put(str, count_set.get(i++));
                } else {
                    poi_score_map.put(str, "0");
                }
            }
            user_poi_score.put(UserId, poi_score_map);
        }

        //2、计算当前的用户与其他的用户之间的相似度
        Map<Integer, Double> poi_score = new HashMap<>();
        Map<Integer, Map<Integer, Double>> similarityMap = new HashMap<>();
        for (Map.Entry<Integer, Set<String>> map : user_poi.entrySet()) {
            if (map.getKey().equals(currentUserID)) {
                //poi_score.put(map.getKey(), 1.0); # 不需要存放和自己的相似度
                continue;
            }
            Double simility = calculator(current_user_poi, map.getValue());
            poi_score.put(map.getKey(), simility);
        }
        //当前的数据结构是{currentUserID:{other1Id:score1,other2Id:score2,....}}
        similarityMap.put(currentUserID, poi_score);

        //3、计算当前用户对于未访问的地点的得分
        //3.1、获得当前用户未访问地点
        Set<String> result = new HashSet<String>();
        result.addAll(poiSet);
        result.removeAll(current_user_poi);
        //3.2、对未访问的地点进行打分
        //poi_score的数据结构是当前用户与其他的用户之间的相似度
        HashMap<Integer, Double> fianl_CF_poi_score = new HashMap<>();
        for (String poi : result) {
            Double score=0.0;
            for (Map.Entry<Integer, Double> user_simility_map : poi_score.entrySet()) {
                //获取相似用户在该地点poi处的打分值 * 用户的相似度
                score += Double.parseDouble(user_poi_score.get(user_simility_map.getKey()).get(poi)) * user_simility_map.getValue();
            }
            fianl_CF_poi_score.put(Integer.valueOf(poi), score);
        }

        //二、计算未访问的位置距离当前的位置的距离，距离得分(key是位置id,value是距离值)
        HashMap<Integer,Double> distanceMap = new HashMap<>();
        for (String poiId : result) {
            Poi poi = poiMapper.selectByPrimaryKey(Integer.valueOf(poiId));
            distanceMap.put(Integer.valueOf(poiId),LocationsUtils.getDistance(Double.valueOf(latitude), Double.valueOf(longitude), Double.valueOf(poi.getLatitude()), Double.valueOf(poi.getLongitude())));
        }

        //三、根据好友关系计算待推荐位置的得分值  公式η*Jaccard_Friend+(1-η)*Jaccard_check_in
        //3.1、计算当前用户与当前用户的好友之间的相似度
        double weight_a = 0.6;
        HashMap<Integer, Double> Jaccard_Friend = new HashMap<>();
        Set<String> currendUser_Friend_set = new LinkedHashSet<>();
        currendUser_Friend_set = relations.get(currentUserID);
        for (Map.Entry<Integer, Set<String>> map : relations.entrySet()) {
            if (map.getKey().equals(currentUserID)) {
                continue;
            }
            //如果此用户是好有的朋友才会去计算两者之间的相似度，否则不计算
            if (currendUser_Friend_set.contains(map.getKey().toString())){
                Double similarity = calculatorFriends(currendUser_Friend_set, map.getValue());
                Jaccard_Friend.put(map.getKey(), similarity);
            }
        }
        //3.2 计算当前用户与其他的用户之间签到地点的相似度（key是当前用户的好友用户，value是当前用户与他的好友用户之间签到的相似度）
        HashMap<Integer, Double> jaccard_check_in = new HashMap<>();
        for (Map.Entry<Integer, Set<String>> map : user_poi.entrySet()) {
            if (currendUser_Friend_set.contains(map.getKey().toString())) {
                Double similarirt = calculatorPOI(current_user_poi, map.getValue());
                jaccard_check_in.put(map.getKey(), similarirt);
            }
        }
        //3.3、计算给待推荐位置的分数值
        HashMap<Integer, Double> fianl_Relation_poi_score = new HashMap<>();
        for (String poi : result) {
            Double score=0.0;
            for (String friendId : currendUser_Friend_set) {
                //获取相似用户在该地点poi处的打分值 * 用户的相似度
                if (!userSet.contains(friendId)) {
                    score += 0.0;
                } else {
                    //如果当前用户的好友不在所有签到的用户中，则score加的值为0
                    score += Double.parseDouble(user_poi_score.get(Integer.valueOf(friendId)).get(poi)) * (Jaccard_Friend.get(Integer.valueOf(friendId)) * weight_a + (1 - weight_a) * jaccard_check_in.get(Integer.valueOf(friendId)));
                }
            }
            fianl_Relation_poi_score.put(Integer.valueOf(poi), score);
        }

        //数据归一化处理
        HashMap<Integer,Double> DistanceScore =new NormailzeUtils().normailzeDistance(distanceMap);
        HashMap<Integer,Double> CFScore = new NormailzeUtils().normailze(fianl_CF_poi_score);
        HashMap<Integer,Double> RelationsScore = new NormailzeUtils().normailze(fianl_Relation_poi_score);

        //6、计算最后的得分值 score = αCountScore + βDistanceScore +(1-α-β)AvgScore ,取α=0.5 取β=0.3
        LinkedHashMap<Integer, Double> FianlScore = new LinkedHashMap<>();
        double a = 0.4, b = 0.4;
        for (Integer key : CFScore.keySet()) {
            if (DistanceScore.containsKey(key) && RelationsScore.containsKey(key)) {
                FianlScore.put(key, a * CFScore.get(key) + b * DistanceScore.get(key) + (1 - a - b) * RelationsScore.get(key));
            }
        }
        //7、对最后的得分进行排序,从大到小
        List<Map.Entry<Integer, Double>> list = new ArrayList<>(FianlScore.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Double>>() {
            @Override
            public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2) {
                return (int)(o2.getValue() - o1.getValue());
            }
        });
        //8、取出推荐前10的id
        int count = 0;
        List<Integer> recommandPoi = new ArrayList<>();
        for(Map.Entry<Integer, Double> t:list){
            if (count++ >= 10) {
                break;
            }
            recommandPoi.add(t.getKey());
        }
        return recommandPoi;

    }

    /**
     * 计算签到位置的相似度
     *
     * @param current_user_poi
     * @param value
     * @return
     */
    private Double calculatorPOI(Set<String> current_user_poi, Set<String> value) {
        //计算集合之间的交集
        Set<String> Intersection = new HashSet<String>();
        Intersection.addAll(current_user_poi);
        Intersection.retainAll(value);
        //并集
        Set<String> union = new HashSet<String>();
        union.addAll(current_user_poi);
        union.addAll(value);
        //计算用户之间好友关系的相似度
        Double similarity = (double) Intersection.size() / (double) union.size();
        return similarity;
    }

    /**
     * 计算用户之间的相似度，只考虑共同的好友数目
     *
     * @param currendUser_friend_set
     * @param value
     * @return
     */
    private Double calculatorFriends(Set<String> currendUser_friend_set, Set<String> value) {
        //计算集合之间的交集
        Set<String> Intersection_result = new HashSet<String>();
        Intersection_result.addAll(currendUser_friend_set);
        Intersection_result.retainAll(value);
        //计算集合之间的并集
        Set<String> union_resullt = new HashSet<String>();
        union_resullt.addAll(currendUser_friend_set);
        union_resullt.addAll(value);
        //计算用户之间好友关系的相似度
        Double similarity = (double) Intersection_result.size() / (double) union_resullt.size();
        return similarity;
    }

    /**
     * 计算两个用户之间的相似度，使用余弦相似度
     * @param current_user_poi：当前用户的签到位置集合
     * @param other_user_poi：其他的用户签到的位置集合
     * @return
     */
    public  Double calculator(Set<String> current_user_poi, Set<String> other_user_poi) {
        //计算签到的集合的交集
        Set<String> result = new HashSet<String>();
        result.addAll(current_user_poi);
        result.retainAll(other_user_poi);
        //交集的长度，分子
        int intersection = result.size();
        //每个集合的长度，分母√ ×√
        double union =Math.sqrt(current_user_poi.size()) * Math.sqrt(other_user_poi.size());
        return intersection / union;
    }



    /**
     * 根据用户的ID查询用户的签到信息
     * @param userId
     * @return
     */
    @Override
    public List<Check> selectCheckByUserId(Integer userId) {
        List<Check> checks = checkMapper.selectCheckByUserId(userId);
        return checks;
    }





}
