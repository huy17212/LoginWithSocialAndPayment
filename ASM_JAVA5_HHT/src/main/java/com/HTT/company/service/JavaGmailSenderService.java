package com.HTT.company.service;

import com.HTT.company.entity.Users;

public interface JavaGmailSenderService {

	void sendEmailActivated(String AddressGmailTo, String hyperlink, Users users);

	void sendEmailWithMultipartFile();

}
