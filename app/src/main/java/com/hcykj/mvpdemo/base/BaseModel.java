package com.hcykj.mvpdemo.base;

import java.io.Serializable;

/**
 * @描述: 基本返回结果类
 * @作者: 宋俊 SongJun 
 * @时间: 2016/10/11 15:48
 */
public class BaseModel<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private int code;
	private String message;
	private boolean isSuccess;
	private T data;

	public BaseModel() {
	}

	public BaseModel(boolean isSuccess, int code, String message) {
		this.code = code;
		this.message = message;
		this.isSuccess = isSuccess;
	}

	public BaseModel(boolean isSuccess, int code, String message, T data) {
		this.code = code;
		this.message = message;
		this.isSuccess = isSuccess;
		this.data = data;
	}


	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean success) {
		isSuccess = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "BaseModel{" +
				"code='" + code + '\'' +
				", message='" + message + '\'' +
				", isSuccess='" + isSuccess + '\'' +
				", data=" + (data != null ? data.toString() : "null") +
				'}';
	}
}
