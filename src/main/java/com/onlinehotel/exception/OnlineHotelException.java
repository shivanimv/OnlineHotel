package com.onlinehotel.exception;

public class OnlineHotelException extends Exception {

	public OnlineHotelException() {
		// TODO Auto-generated constructor stub
		System.out.println("This is Online Hotel Exception");
	}

	public OnlineHotelException(String message) {
		super(message);
		System.out.println("Roll back happened");
		// TODO Auto-generated constructor stub
	}

	public OnlineHotelException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public OnlineHotelException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public OnlineHotelException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

}
