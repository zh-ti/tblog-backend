package com.tian.tblog.controller;

import com.tian.tblog.service.impl.UploadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/tblog/upload")
public class UpLoadController {

    @Autowired
    private UploadServiceImpl service;
 
    @PostMapping("/uploadImage/{type}/{documentId}")
    public String upload(@PathVariable("type") int type, @PathVariable("documentId") String documentId, @RequestParam("image")MultipartFile file){
        return service.uploadImage(file, type, documentId);
    }

    @PostMapping("/multiUpload")
    public String multiUpload(@RequestParam("file")MultipartFile[] files){
        String[] paths = service.multiUpload(files);
        return "";
    }
}