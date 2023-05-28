package com.app.vps.exception;

public class ApplicationException extends Exception {

	private static final long serialVersionUID = 1L;
	private int code;
	private String message = null;
	private Object msg;

	public ApplicationException(int code, String message) {
		super(message);
		this.code = code;
		this.message=message;
	}
	

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public Object getMsg() {
		return msg;
	}

	public void setMsg(Object msg) {
		this.msg = msg;
	}


	public void setCode(int code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}