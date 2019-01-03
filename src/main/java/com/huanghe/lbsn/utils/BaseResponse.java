package com.huanghe.lbsn.utils;

/**
 * 
 * @author wangwei
 * @date 2017年6月16日
 * @param <T>
 */
public class BaseResponse<T> {
	private int status;
	private String msg;
	private long time;
	private Object objectbean;

	/**
	 * 
	 */
	public BaseResponse() {
		super();
	}

	/**
	 * @param responseMSG
	 */
	public BaseResponse(ResponseMSG responseMSG) {
		super();
		this.status = responseMSG.getStatus();
		this.msg = responseMSG.getMsg();
		this.time = System.currentTimeMillis();
	}

	/**
	 * @param responseMSG
	 * @param obj
	 */
	public BaseResponse(ResponseMSG responseMSG, Object objectbean) {
		super();
		this.status = responseMSG.getStatus();
		this.msg = responseMSG.getMsg();
		this.time = System.currentTimeMillis();
		this.objectbean = objectbean;
	}

	/**
	 * @param responseMSG
	 * @param msg
	 * @param obj
	 */
	public BaseResponse(ResponseMSG responseMSG, String msg, Object objectbean) {
		super();
		this.status = responseMSG.getStatus();
		if (msg != null && !"".equals(msg)) {
			this.msg = msg;
		} else {
			this.msg = responseMSG.getMsg();
		}
		this.time = System.currentTimeMillis();
		this.objectbean = objectbean;
	}

	/**
	 * 返回成功信息
	 * 
	 * @param msg
	 * @param obj
	 */
	public BaseResponse(String msg, Object objectbean) {
		super();
		this.status = 1;
		this.msg = msg;
		this.time = System.currentTimeMillis();
		this.objectbean = objectbean;
	}


	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public Object getObjectbean() {
		return objectbean;
	}

	public void setObjectbean(Object objectbean) {
		this.objectbean = objectbean;
	}

}
