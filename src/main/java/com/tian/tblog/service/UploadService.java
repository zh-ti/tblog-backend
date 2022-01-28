package com.tian.tblog.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    String uploadFile(MultipartFile multipartFile);
    String[] multiUpload(MultipartFile[] files);
    String uploadImage(MultipartFile file, int type, String documentId);
    String uploadCoverImage(MultipartFile multipartFile, int type, String documentId);
}
