package com.react.api.exceptions.custom;


public class NotFoundException extends RuntimeException{

	private static final long serialVersionUID = 4487532400325984393L;

	public NotFoundException(String str) {
		super(str);
	}
	
}