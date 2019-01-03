package com.huanghe.lbsn.utils;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
/**
 * @Author huanghe
 * @Date 2018/12/27 16:38
 * @Description
 */
public class TokenTools {

    /**
     * 生成token放入session
     * @param request
     * @param tokenServerkey
     */
    public static void saveToken(HttpServletRequest request,String tokenServerkey,Object value){
        request.getSession().setAttribute(tokenServerkey, value);
    }

    /**
     * 移除token
     * @param request
     * @param tokenServerkey
     */
    public static void removeToken(HttpServletRequest request,String tokenServerkey){
        request.getSession().removeAttribute(tokenServerkey);
    }

    /**
     * 设置session的过期时间
     */
    public static void setExpireTime(HttpServletRequest request, int time) {
        request.getSession().setMaxInactiveInterval(time);
    }

    /**
     * 判断请求参数中的token是否和session中一致
     * @param request
     * @param tokenClientkey
     * @param tokenServerkey
     * @return
     */
    public static boolean judgeTokenIsEqual(HttpServletRequest request,String tokenClientkey,String tokenServerkey){
        String token_client = request.getParameter(tokenClientkey);
        if(StringUtils.isEmpty(token_client)){
            return false;
        }
        String token_server = (String) request.getSession().getAttribute(tokenServerkey);
        if(StringUtils.isEmpty(token_server)){
            return false;
        }

        if(!token_server.equals(token_client)){
            return false;
        }

        return true;
    }

}
