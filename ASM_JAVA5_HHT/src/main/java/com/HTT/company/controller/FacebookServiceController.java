package com.HTT.company.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.HTT.company.service.UsersService;
import com.HTT.company.util.JavaLoginWithFacebookUtils;

@Controller
public class FacebookServiceController {

	@Autowired
	JavaLoginWithFacebookUtils facebookUtils;

	@Autowired
	UsersService usersService;

	@GetMapping("LoginWithFacebook")
	public String loginWithGoogle(HttpServletRequest request, @RequestParam("code") String codeToken)
			throws ClientProtocolException, IOException {

		String accessToken = facebookUtils.getToken(codeToken);
		User user = facebookUtils.getUserInfo(accessToken);
		
		System.out.println(user.toString());
//
//		GoogleDto user = googleUtil.getUserInfo(googleUtil.getToken(codeToken));
//
//		// create principal
//		UserDetails userDetail = googleUtil.buildUser(user);
//
//		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
//				userDetail.getAuthorities());
//
//		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//
//		// check the duplicated in database, if true -> create new account, nor login
//		// with that account.
//		Optional<Users> userTemporaty = Optional.ofNullable(usersService.findByGmail(user.getEmail()));
//
//		if (userTemporaty.isEmpty()) {
//			Users parsingUserEntity = GoogleDtoUtils.GoogleDtoParseToUsers(user);
//			usersService.create(parsingUserEntity);
//		} else {
//			return "views/RegisterForm";
//		}

		return "views/RegisterForm";
	}
}
