package com.elarsenaldecamisetas.app.service.impl;

import com.elarsenaldecamisetas.app.service.GoogleDriveService;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.Permission;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class GoogleDriveServiceImpl implements GoogleDriveService {

    private final Drive driveService;
    private final String googleDriveFolderId;

    @Override
    public String uploadImage(MultipartFile file) {
        try {
            File fileMetadata = new File();
            fileMetadata.setName(file.getOriginalFilename());
            fileMetadata.setParents(Collections.singletonList(googleDriveFolderId));

            InputStreamContent mediaContent = new InputStreamContent(
                    file.getContentType(), file.getInputStream());

            File uploadedFile = driveService.files()
                    .create(fileMetadata, mediaContent)
                    .setFields("id, name")
                    .execute();

            Permission permission = new Permission();
            permission.setType("anyone");
            permission.setRole("reader");
            driveService.permissions()
                    .create(uploadedFile.getId(), permission)
                    .execute();

            return "https://drive.google.com/uc?export=view&id=" + uploadedFile.getId();

        } catch (IOException e) {
            throw new RuntimeException("Error uploading image to Google Drive", e);
        }
    }

    @Override
    public void deleteImage(String fileUrl) {
        try {
            String fileId = extractFileId(fileUrl);
            driveService.files().delete(fileId).execute();
        } catch (IOException e) {
            throw new RuntimeException("Error deleting image from Google Drive", e);
        }
    }

    private String extractFileId(String url) {
        return url.substring(url.lastIndexOf("id=") + 3);
    }
}