package com.HTT.company.service;

import com.HTT.company.entity.Users;
import com.HTT.company.service.commonService.CRUDService;

public interface UsersService extends CRUDService<Users, String> {
	
	Users findByUserName(String username);
	
	Users findByGmail(String gmail);
	
}
