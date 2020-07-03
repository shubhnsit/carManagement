package com.example.CarManagement.response;

public enum GenericMessages {
	CAR_REGISTERED("Car Registered Successfully"),
	BIDDING_REQUEST_SUCCESSFUL("Bidding for this Auction has been registered successfully"),
	USER_ADD_SUCCESSFUL("User has been added successfully"),
	AUCTION_STATE_CHANGED("Auction State has been updated from %s to %s for this car");

	String message;

	GenericMessages(String message) {
		this.message = message;
	}

	public String message() {
		return message;
	}

}