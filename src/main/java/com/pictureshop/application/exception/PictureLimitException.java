package com.pictureshop.application.exception;

public class PictureLimitException extends Exception { 
	
	
	private static final long serialVersionUID = 1L;

	public static final String PICTURE_LIMIT = "Maximum size reached. It's not possible to add more pictures to this shop.";
	
	public PictureLimitException(String message) {
		super(message);
	}

}
