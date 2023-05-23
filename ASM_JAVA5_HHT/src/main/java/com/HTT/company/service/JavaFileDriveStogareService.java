package com.HTT.company.service;

import java.io.IOException;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

public interface JavaFileDriveStogareService {
	
	void createNewFolderDrive(String name);
	
	File addNewAvatarToNewFolder(String username, String avatar, String gmailShared);
	
	void deleteOldAvatar(String username);
	
	void addPermission(String Gmail, File fileHasToPermission);
	
	Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException;

//	public static void main(String... args) throws IOException, GeneralSecurityException {
//		// Build a new authorized API client service.
//		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
//		Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
//				.setApplicationName(APPLICATION_NAME).build();
//
//		// Print the names and IDs for up to 10 files.
//		FileList result = service.files().list().setPageSize(10).setFields("nextPageToken, files(id, name)").execute();
//		List<File> files = result.getFiles();
//		if (files == null || files.isEmpty()) {
//			System.out.println("No files found.");
//		} else {
//			System.out.println("Files:");
//			for (File file : files) {
//				if (file.getName().equals("kakaHuyTri")) {
//					System.out.printf("%s (%s)\n", file.getName(), file.getId());
//				}
//			}
//		}
//
//		File fileMetada = new File();
//		fileMetada.setName("kakaHuyTri");
//		fileMetada.setMimeType("application/vnd.google-apps.folder");
//		File dat = service.files().create(fileMetada).setFields("id").execute();
//		System.out.println("this is kaka" + dat.getId());
//
//		File fileMetadata2 = new File();

		// start upload images
//		File fileMetadata = new File();
//		fileMetadata.setName("kakaHuyTri.jpg");
//		java.io.File filePath = new java.io.File("kakaHuyTri.jpg");
//		FileContent mediaContent = new FileContent("image/jpg", filePath);
//		File filek = service.files().create(fileMetadata, mediaContent).setFields("id").execute();
//		System.out.println("File ID: " + filek.getId());

//		final List<String> ids = new ArrayList<String>();
//
//		JsonBatchCallback<Permission> callback = new JsonBatchCallback<Permission>() {
//			@Override
//			public void onFailure(GoogleJsonError e, HttpHeaders responseHeaders) throws IOException {
//				System.err.println(e.getMessage());
//			}
//
//			public void onSuccess(Permission permission, HttpHeaders responseHeaders) throws IOException {
//				System.out.println("Permission ID: " + permission.getId());
//				ids.add(permission.getId());
//
//			}
//		};
//		
//		OutputStream outputStream = new FileOutputStream("C:\\Users\\Huy1721\\Desktop\\" + "file.png");
//		service.files().get(filek.getId()).executeMediaAndDownloadTo(outputStream);
//		outputStream.flush();
//		outputStream.close();
//
//		BatchRequest batch = service.batch();
//		Permission userPermission = new Permission().setType("user").setRole("writer");
//
//		userPermission.setEmailAddress("hahuytri2K3@gmail.com");
//		service.permissions().create(filek.getId(), userPermission).setFields("id").queue(batch, callback);
//
//		Permission domainPermission = new Permission().setType("domain").setRole("reader");
//		domainPermission.setDomain(APPLICATION_NAME);
//
//		service.permissions().create(filek.getId(), domainPermission).setFields("id").queue(batch, callback);
//
//		batch.execute();
//	}

}
