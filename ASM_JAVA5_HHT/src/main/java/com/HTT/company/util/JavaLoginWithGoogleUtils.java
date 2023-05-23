package com.HTT.company.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.HTT.company.constant.GoogleLoginTokenConstant;
import com.HTT.company.dto.GoogleDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@Component
public class JavaLoginWithGoogleUtils {

	public String getToken(final String code) throws ClientProtocolException, IOException {

		String response = Request.Post(GoogleLoginTokenConstant.GOOGLE_LINK_GET_TOKEN)
				.bodyForm(Form.form().add("client_id", GoogleLoginTokenConstant.GOOGLE_CLIENT_ID)
						.add("client_secret", GoogleLoginTokenConstant.GOOGLE_CLIENT_SECRET)
						.add("redirect_uri", GoogleLoginTokenConstant.GOOGLE_REDIRECT_URI).add("code", code)
						.add("grant_type", "authorization_code").build())
				.execute().returnContent().asString();

		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(response).get("access_token");
		return node.textValue();
	}

	public GoogleDto getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
		String link = GoogleLoginTokenConstant.GOOGLE_LINK_GET_USER_INFO + accessToken;
		String response = Request.Get(link).execute().returnContent().asString();
		GoogleDto googlePojo = new Gson().fromJson(response, GoogleDto.class);
		return googlePojo;

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
