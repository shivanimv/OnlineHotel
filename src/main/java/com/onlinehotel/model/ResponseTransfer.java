package com.onlinehotel.model;

public class ResponseTransfer {

	
	private String messsage;
	
	public ResponseTransfer(String messsage) {
		super();
		this.messsage = messsage;
	}

	public String getMesssage() {
		return messsage;
	}

	public void setMesssage(String messsage) {
		this.messsage = messsage;
	}

	public ResponseTransfer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ResponseTransfer [messsage:" + messsage + "]";
	}

}
