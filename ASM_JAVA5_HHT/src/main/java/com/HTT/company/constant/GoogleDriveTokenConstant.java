package com.HTT.company.constant;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;

public class GoogleDriveTokenConstant {

	private static final String APPLICATION_NAME = "Google Drive API Java Quickstart";
	
	private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

	private static final String TOKENS_DIRECTORY_PATH = "tokens";
	
	private static final List<String> SCOPES = Collections.singletonList("https://www.googleapis.com/auth/drive");
	
	private static final String CREDENTIALS_FILE_PATH = "/client_secret.json";
	
}
