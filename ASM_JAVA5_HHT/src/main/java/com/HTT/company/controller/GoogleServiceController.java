package com.HTT.company.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.HTT.company.dto.GoogleDto;
import com.HTT.company.entity.Users;
import com.HTT.company.service.UsersService;
import com.HTT.company.util.GoogleDtoUtils;
import com.HTT.company.util.JavaLoginWithGoogleUtils;

@Controller
public class GoogleServiceController {

	@Autowired
	JavaLoginWithGoogleUtils googleUtil;

	@Autowired
	UsersService usersService;

	@GetMapping("LoginWithGoogle")
	public String loginWithGoogle(HttpServletRequest request, @RequestParam("code") String codeToken)
			throws ClientProtocolException, IOException {

		GoogleDto user = googleUtil.getUserInfo(googleUtil.getToken(codeToken));

		// create principal
		UserDetails userDetail = googleUtil.buildUser(user);

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
				userDetail.getAuthorities());

		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// check the duplicated in database, if true -> create new account, nor login
		// with that account.
		Optional<Users> userTemporaty = Optional.ofNullable(usersService.findByGmail(user.getEmail()));

		if (userTemporaty.isEmpty()) {
			Users parsingUserEntity = GoogleDtoUtils.GoogleDtoParseToUsers(user);
			usersService.create(parsingUserEntity);
		} else {
			return "views/RegisterForm";
		}

		return "views/RegisterForm";
	}
}
