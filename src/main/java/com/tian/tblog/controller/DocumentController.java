package com.tian.tblog.controller;

import com.tian.tblog.service.DocumentService;
import com.tian.tblog.utils.JsonHandler;
import com.tian.tblog.utils.URLCoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tblog/document")
@CrossOrigin(origins = "*")
public class DocumentController {

    @Autowired
    private DocumentService service;

    @RequestMapping("/getDocumentList")
    public String getDocumentList(){
        return JsonHandler.stringify(service.documentList());
    }

    @GetMapping("/getDocument/{id}")
    public String queryDocument(@PathVariable("id")String id){
        return JsonHandler.stringify(service.queryDocument(id));
    }

    @GetMapping("/deleteDocument/{id}")
    public String deleteDocument(@PathVariable("id") String id){
        return JsonHandler.stringify(service.deleteDocument(id));
    }

    @PostMapping("/addDocument")
    public String addDocument(@RequestBody String params) {
        return JsonHandler.stringify(service.addDocument(URLCoder.decode(params)));
    }

    @PostMapping("/updateDocument")
    public String updateDocument(@RequestBody String params) {
        return JsonHandler.stringify(service.updateDocument(URLCoder.decode(params)));
    }

    @GetMapping("/getUnpublishedDocList")
    public String getUnpublishedDocList(){
        return JsonHandler.stringify(service.unpublishedDocList());
    }

    @GetMapping("/withdrawDocument/{id}")
    public String withdrawDocument(@PathVariable("id") String id){
        return JsonHandler.stringify(service.withdrawDocument(id));
    }

    @PostMapping("/publishDocument")
    public String publishDocument(@RequestBody String params) {
        return JsonHandler.stringify(service.publishDocument(URLCoder.decode(params)));
    }
}
