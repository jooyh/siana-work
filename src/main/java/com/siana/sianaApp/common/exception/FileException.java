package com.siana.sianaApp.common.exception;

@SuppressWarnings("serial")
public class FileException extends Exception{

	private final int ERR_CODE;

	public FileException(String msg , int errCode) {
		super(msg);
		this.ERR_CODE = errCode;
	}

	public FileException(String msg) {
		this(msg,100);
	}

	public int getErrCode() {
		return this.ERR_CODE;
	}
}
