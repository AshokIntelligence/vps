package com.app.vps.mail;

import com.app.vps.exception.ApplicationException;

public interface IMailService {
	
	void sendMail(String to,String cc, String[] mailIds, String msgBody, String msgSubject, String from) throws ApplicationException, Exception;
}
