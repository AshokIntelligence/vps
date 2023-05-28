package com.app.vps.mail;

import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.vps.exception.ApplicationException;

@Service
@Transactional(rollbackFor = {ApplicationException.class, Exception.class })
public class MailServiceImpl implements IMailService{
	
	@Autowired
    private JavaMailSender emailSender;

	@Override
	@Async
	public void sendMail(String to, String cc, String[] mailIds, String msgBody, String msgSubject, String from)
			throws ApplicationException, Exception {
			to="thekaustubhkhadke@gmail.com";
			try {
			 	MimeMessage message = emailSender.createMimeMessage();
		        MimeMessageHelper helper = new MimeMessageHelper(message);
				message.setFrom(from);
				if(mailIds.length==0 && cc==null && to!=null) {
					helper.setTo(to);
				}else
				if(mailIds.length!=0 && cc!=null && to==null) {
					helper.setTo(mailIds);
					helper.setCc(cc);
				}else
				if(mailIds.length!=0 && cc==null && to!=null) {
					helper.setTo(mailIds);
					helper.setCc(to);
				}
				else if(mailIds.length!=0 && cc==null && to==null) {
					helper.setTo(mailIds);
				} 
				else if(mailIds.length==0 && cc!=null && to!=null) {
					helper.setTo(to);
					helper.setCc(cc);
				}else {
					helper.setTo(to);
				}
		        helper.setSubject(msgSubject);
		        helper.setText(msgBody);
		        message.setContent(msgBody, "text/html"); 
				emailSender.send(message);
			}     		
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}


