package com.huanghe.lbsn.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @Author huanghe
 * @Date 2018/12/27 15:11
 * @Description
 */
public class RestRespone {
    @JsonIgnore
    public static int STATUS_CODE_ERR_NONE = 0x01;
    @JsonIgnore
    public static int STATUS_CODE_ERR_UNKNOWN = 0x01;
    public int status;
    public String msg;
    public Object objectbean;
    public long time;


    public RestRespone(){
        this.time = System.currentTimeMillis();
        this.status = RestRespone.STATUS_CODE_ERR_NONE;
    }
    public RestRespone(String msg, Object ob){
        this.time = System.currentTimeMillis();
        this.status = RestRespone.STATUS_CODE_ERR_NONE;
        this.msg = msg;
        this.objectbean = ob;
    }
    public RestRespone(int code, String msg, Object ob){
        this.time = System.currentTimeMillis();
        this.status = code;
        this.msg = msg;
        this.objectbean = ob;
    }
}
