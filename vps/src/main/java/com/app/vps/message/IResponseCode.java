package com.app.vps.message;

public interface IResponseCode {
	int SUCCESS = 200;
	int DATA_NOT_FOUND = 204;
	int UNAUTHORIZED = 401;
	int SERVER_ERROR = 501;
	int EXISTING_ENTITY = 1001;
	int INVALID_ENTITY = 1002;
	int INVALID_STATUS = 1003;
	int INVALID_DATA = 1004;
	int USER_NOT_FOUND = 1005;
	int INVALID_TOKEN = 1011;
	int INVALID_DEVICEID = 1012;
}
