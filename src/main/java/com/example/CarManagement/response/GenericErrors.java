package com.example.CarManagement.response;

public enum GenericErrors {
	INVALID_USER("User not authorized for this request"),
	USER_NOT_FOUND("User not found with this id"),
	CAR_NOT_FOUND("Could not found car with this Id"),
	CAR_REGISTERED_FOR_AUCTION("Car has been registered for Auction"),
	CAR_NOT_REGISTERED_FOR_AUCTION("Car has not been registered for Auction"),
	INVALID_AUCTION_STATE("Please provide valid auction state"),
	INVALID_USER_TYPE("Please provide valid user type"),
	INVALID_AUCTION_STATE_MOVE("Could not change car with existing state as %s to new new state %s"),
	NO_AVAILABLE_CAR_SELLER("No available cars for this seller"),
	NO_AVAILABLE_CAR_ADMIN("System do not have any car available car"),
	AUCTION_NOT_COMPLETED("Something went wrong during auction"),
	USER_NOT_REGISTERED("Something went wrong during user registration"),
	NO_BIDDING_HISTORY_FOUND("No Bidding Found"),
	NO_AVAILABLE_CAR("Currently, we are out of stock, will be back soon");

	String message;

	GenericErrors(String message) {
		this.message = message;
	}

	public String message() {
		return message;
	}

}