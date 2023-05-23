package com.HTT.company.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.HTT.company.constant.FacebookLoginTokenConstant;
import com.HTT.company.dto.FacebookDto;
import com.HTT.company.dto.GoogleDto;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;

@Component
public class JavaLoginWithFacebookUtils {

	public String getToken(final String code) throws ClientProtocolException, IOException {

		String link = String.format(FacebookLoginTokenConstant.FACEBOOK_LINK_GET_TOKEN, FacebookLoginTokenConstant.FACEBOOK_APP_ID,
				FacebookLoginTokenConstant.FACEBOOK_APP_SECRET, FacebookLoginTokenConstant.FACEBOOK_REDIRECT_URL, code);
		String response = Request.Get(link).execute().returnContent().asString();
		JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
		return jobj.get("access_token").toString().replaceAll("\"", "");
	}

	public User getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
		FacebookClient facebookClient = new DefaultFacebookClient(accessToken,
				FacebookLoginTokenConstant.FACEBOOK_APP_SECRET, Version.LATEST);
		return facebookClient.fetchObject("me", User.class);
	}

	public UserDetails buildUser(GoogleDto googlePojo) {
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		UserDetails userDetail = new User(googlePojo.getEmail(), "", enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
		return userDetail;
	}

}
