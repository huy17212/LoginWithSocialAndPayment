package com.HTT.company.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.HTT.company.entity.Users;

@Configuration
public class AppBean {
	
	// lưu lại token cho remember me
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		return new InMemoryTokenRepositoryImpl();
	}
	
	//lấy bộ băm bcrypt
	@Bean
	public BCryptPasswordEncoder getBcryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public Users getUsers() {
		return new Users();
	}

}
