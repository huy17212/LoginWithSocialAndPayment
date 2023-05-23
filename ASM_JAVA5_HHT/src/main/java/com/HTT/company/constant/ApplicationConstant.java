package com.HTT.company.constant;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.HTT.company.bean.AppBean;


public class ApplicationConstant {

	public static AnnotationConfigApplicationContext APPLICATION_CONTEXT = new AnnotationConfigApplicationContext(AppBean.class);
	
	public static String GMAIL_ADDRESS_COMPANY = "hahuytridevjava4ever@gmail.com";
	
	public static String TRUE_TOKEN_FOR_CREATE_NEW_ACCOUNT = "";
	
	public static String GENERATE_RANDOM_TOKEN_FOR_CREATE_NEW_ACCOUT() {
		return Math.random() + "";
	}
	
}
