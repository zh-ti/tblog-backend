package com.tian.tblog.service.impl;

import com.tian.tblog.bean.Image;
import com.tian.tblog.mapper.ImageMapper;
import com.tian.tblog.service.UploadService;
import com.tian.tblog.utils.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    ImageMapper mapper;

    @Value("${file.upload.article.image}")
    private String articleImagePath;

    private String baseUrl = "http://localhost:8088/tblog/";

    @Override
    public String uploadImage(MultipartFile file, int type, String documentId) {
        String url = "" ;
        if(type == 0){
            url = saveFile(file, articleImagePath, UUIDGenerator.getShortUUID());
        }else if(type == 1){
            url = uploadCoverImage(file, type, documentId);
        }
        if(url.equals("fail")) return "fail";
        return baseUrl + url;
    }

    @Override
    public String uploadCoverImage(MultipartFile articleImage, int type, String documentId) {
        String url = "" ;
        String path = "";
        String filename = "";
        Map<String, Object> params = new HashMap<>();
        params.put("documentId", documentId);
        params.put("type", type);
        Image result = mapper.isExistCover(params);
        if(result  == null){
            filename = UUIDGenerator.getShortUUID();
            path = saveFile(articleImage, articleImagePath, filename);
            url = baseUrl + path;
            mapper.addImage(new Image(filename, type, documentId, url, path));
        }else{
            try {
                File file = ResourceUtils.getFile("classpath:"+ result.getPath());
                file.delete();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            filename = result.getId();
            path = saveFile(articleImage, articleImagePath, filename);
            url = baseUrl + path;
            mapper.updateImage(new Image(filename, type, documentId, url, path));
        }
        return path;
    }

    @Override
    public String uploadFile(MultipartFile articleImage) {
        return null;
    }

    @Override
    public String[] multiUpload(MultipartFile[] files) {
        String[] paths = new String[files.length];
        System.out.println("文件的个数:"+files.length);
        for (int i = 0; i < files.length; i++){
            paths[i] = saveFile(files[i], articleImagePath, UUIDGenerator.getShortUUID());
        }
        return paths;
    }



    private String saveFile(MultipartFile file, String basePath, String filename) {
        if (file.isEmpty()){
            return "未选择文件";
        }
//        String filename = file.getOriginalFilename(); //获取上传文件原来的名称
        String[] split = file.getOriginalFilename().split("\\.");
        String suffix = split[split.length - 1];
        String savePath = basePath + filename + "." + suffix;
        File temp = new File(basePath);
        if (!temp.exists()){
            temp.mkdirs();
        }
        String path = "";
        try {
            path = ResourceUtils.getURL("classpath:").getPath()+savePath;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        File localFile = new File(path);
        System.out.println("file"+localFile);
        try {
            file.transferTo(localFile); //把上传的文件保存至本地
        }catch (IOException e){
            e.printStackTrace();
            return "fail";
        }

        return savePath;
    }

}
