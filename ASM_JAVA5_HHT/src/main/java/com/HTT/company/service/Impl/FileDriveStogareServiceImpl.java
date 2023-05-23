package com.HTT.company.service.Impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;

import com.HTT.company.service.JavaFileDriveStogareService;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.batch.BatchRequest;
import com.google.api.client.googleapis.batch.json.JsonBatchCallback;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.Permission;

@Service
public class FileDriveStogareServiceImpl implements JavaFileDriveStogareService {

	private static final String APPLICATION_NAME = "Google Drive API Java Quickstart";
	private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	static final String TOKENS_DIRECTORY_PATH = "tokens";
	private static final List<String> SCOPES = Collections.singletonList("https://www.googleapis.com/auth/drive");
	private static final String CREDENTIALS_FILE_PATH = "/client_secret.json";

	@Override
	public void createNewFolderDrive(String name) {
		try {
			final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY,
					new FileDriveStogareServiceImpl().getCredentials(HTTP_TRANSPORT))
					.setApplicationName(APPLICATION_NAME).build();

			File fileMetadaFolder = new File();
			fileMetadaFolder.setName(name);
			fileMetadaFolder.setMimeType("application/vnd.google-apps.folder");
			service.files().create(fileMetadaFolder).setFields("id").execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public File addNewAvatarToNewFolder(String username, String avatar, String gmailShared) {
		try {
			final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY,
					new FileDriveStogareServiceImpl().getCredentials(HTTP_TRANSPORT))
					.setApplicationName(APPLICATION_NAME).build();

			File fileMetaDataFolder = new File();
			fileMetaDataFolder.setName(username);
			fileMetaDataFolder.setMimeType("application/vnd.google-apps.folder");
			File folderAvatar = service.files().create(fileMetaDataFolder).setFields("id").execute();

			File fileMetaAvatar = new File();
			fileMetaAvatar.setName(username + " Infomation");
			fileMetaAvatar.setParents(Collections.singletonList(folderAvatar.getId()));
			java.io.File filePath = new java.io.File("uploads/" + avatar);
			FileContent mediaContent = new FileContent("image/*", filePath);
			File FileAvatar =  service.files().create(fileMetaAvatar, mediaContent).setFields("id").execute();

			return FileAvatar;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public void deleteOldAvatar(String username) {

	}

	@Override
	public Credential getCredentials(NetHttpTransport HTTP_TRANSPORT) throws IOException {

		InputStream in = FileDriveStogareServiceImpl.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
		if (in == null) {
			throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
		}
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				clientSecrets, SCOPES)
				.setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
				.setAccessType("offline").build();
		LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
		return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");

	}

	@Override
	public void addPermission(String Gmail, File fileHaveToPermission) {
		try {
			final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY,
					new FileDriveStogareServiceImpl().getCredentials(HTTP_TRANSPORT))
					.setApplicationName(APPLICATION_NAME).build();
			final List<String> ids = new ArrayList<String>();

			JsonBatchCallback<Permission> callback = new JsonBatchCallback<Permission>() {
				@Override
				public void onFailure(GoogleJsonError e, HttpHeaders responseHeaders) throws IOException {
					System.err.println(e.getMessage());
				}

				public void onSuccess(Permission permission, HttpHeaders responseHeaders) throws IOException {
					System.out.println("Permission ID: " + permission.getId());
					ids.add(permission.getId());
				}
			};

			BatchRequest batch = service.batch();
			Permission userPermission = new Permission().setType("user").setRole("reader");

			userPermission.setEmailAddress(Gmail);

			service.permissions().create(fileHaveToPermission.getId(), userPermission).setFields("id").queue(batch,
					callback);

			Permission domainPermission = new Permission().setType("domain").setRole("reader");
			domainPermission.setDomain(APPLICATION_NAME);

			service.permissions().create(fileHaveToPermission.getId(), domainPermission).setFields("id").queue(batch,
					callback);

			batch.execute();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

}
