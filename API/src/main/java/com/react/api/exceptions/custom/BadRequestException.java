package com.react.api.exceptions.custom;


public class BadRequestException extends RuntimeException{

	private static final long serialVersionUID = 7838335120454017018L;

	public BadRequestException(String str) {
		super(str);
	}
	
}