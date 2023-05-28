package com.app.vps.message;

public interface IResponseMessage {
	String UNAUTHORIZED_USER = "Invalid Credentials.";
	String INVALID_MASTER_ID = "Invalid Master Id.";
	String INVALIDE_USER_ID = "Invalid Id.";
	String SERVER_ERROR = "Server Error.";
	String SUCCESS = "success";
	String LOGGEDOUT_SUCCESSFULLY = "Logout Successfully.";
	String ALREADY_LOGGEDOUT = "Already Logout.";

	
	String DATA_NOT_FOUND = "Data Not Found.";
	String PROFILE_UPDATED_SUCCESSFULLY = "Profile Update Successfully.";
	String PASSWORD_UPDATED_SUCCESSFULLY = "Your Password Update Successfully.";
	String INVOICE_DETAILS_SAVE_SUCCESSFULLY = "Invoice Details Save Successfully";
}
