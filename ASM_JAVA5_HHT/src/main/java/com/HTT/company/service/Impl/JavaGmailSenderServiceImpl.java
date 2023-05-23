package com.HTT.company.service.Impl;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HTT.company.entity.Users;
import com.HTT.company.service.JavaGmailSenderService;
import com.HTT.company.util.JavaGmailSenderUtils;

@Service
public class JavaGmailSenderServiceImpl implements JavaGmailSenderService {
	
	@Autowired
	JavaGmailSenderUtils javaGmailSenderUtil;
	
	
	@Override
	public void sendEmailActivated(String AddressGmailTo, String hyperlink, Users users) {
		try {
			javaGmailSenderUtil.sendEmailActivated(AddressGmailTo, hyperlink, users);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendEmailWithMultipartFile() {
		// TODO Auto-generated method stub
		
	}

	

}
