/**
 * 
 */
package com.huanghe.lbsn.utils;

/**
 * @author wangwei
 * @date 2017年6月16日
 */
public enum ResponseMSG {

	/**
	 * @param code
	 * @param msg
	 */
	ERROR(0, "error"), SUCCESS(1, "success"), 
	E_USER_ERROR(0, "用户名或密码错误!"),NO_MESSAGE(2,"没有查到所需的数据!"),
	;

	private int status;
	private String msg;

	/**
	 * @param code
	 * @param msg
	 * @param time
	 */
	private ResponseMSG(int status, String msg) {
		this.status = status;
		this.msg = msg;
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

	

}
