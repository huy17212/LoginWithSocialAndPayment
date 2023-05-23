package com.HTT.company.util;

import com.HTT.company.constant.ApplicationConstant;
import com.HTT.company.dto.GoogleDto;
import com.HTT.company.entity.Users;

public class GoogleDtoUtils {

	public static Users GoogleDtoParseToUsers(GoogleDto entityGoogleDto) {

		Users entityUsers = (Users) ApplicationConstant.APPLICATION_CONTEXT.getBean("getUsers");
		entityUsers.setUsername(entityGoogleDto.getEmail());
		entityUsers.setPassword("");
		entityUsers.setAccountName(entityGoogleDto.getFamily_name() + " " + entityGoogleDto.getGiven_name());
		entityUsers.setNumberPhone("");
		entityUsers.setGmail(entityGoogleDto.getEmail());
		entityUsers.setAvatar(entityGoogleDto.getPicture());
		return entityUsers;
	}

}
