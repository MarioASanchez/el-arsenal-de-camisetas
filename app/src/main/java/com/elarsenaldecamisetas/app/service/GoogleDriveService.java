package com.elarsenaldecamisetas.app.service;

import org.springframework.web.multipart.MultipartFile;

public interface GoogleDriveService {
    public String uploadImage(MultipartFile file);
    public void deleteImage(String fileUrl);
}
