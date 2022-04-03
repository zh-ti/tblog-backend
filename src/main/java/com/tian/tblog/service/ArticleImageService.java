package com.tian.tblog.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface ArticleImageService {
    String uploadArticleImage(MultipartFile file);
    String uploadFile(MultipartFile multipartFile);
    String[] multiUpload(MultipartFile[] files);
    String uploadImage(MultipartFile file, int type, String documentId);
    String uploadCoverImage(MultipartFile multipartFile, int type, String documentId);
    byte[] getArticleImage(String filename) throws IOException;
}
