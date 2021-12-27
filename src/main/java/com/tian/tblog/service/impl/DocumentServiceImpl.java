package com.tian.tblog.service.impl;

import com.tian.tblog.bean.Document;
import com.tian.tblog.mapper.DocumentMapper;
import com.tian.tblog.service.DocumentService;
import com.tian.tblog.utils.DateUtils;
import com.tian.tblog.utils.JsonHandler;
import com.tian.tblog.utils.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentMapper mapper;

    @Override
    public Document queryDocument(String id) {
        return mapper.getDocument(id);
    }

    @Override
    public Document queryDocumentByTitle(String title) {
        return mapper.getDocumentByTitle(title);
    }

    @Override
    public List<Document> unpublishedDocList() {
        return mapper.getUnpublishedDocList();
    }

    @Override
    public List<Document> documentList() {
        return mapper.getDocumentList();
    }

    @Override
    public int deleteDocument(String id) {
        return mapper.deleteDocument(id);
    }

    @Override
    public String addDocument(String params){
        Map<String, Object> resultMap = new HashMap<>();
        String uuid = UUIDGenerator.getShortUUID();
        Map<String, Object> paramsMap = JsonHandler.parse(params, Map.class);
        paramsMap.put("id", uuid);
        paramsMap.put("lastEdit", DateUtils.getFormatDate());
        if(mapper.getDocumentByTitle((String)paramsMap.get("title")) == null){
            int result = mapper.addDocument(paramsMap);
            resultMap.put("result", result);
            resultMap.put("id", uuid);
            return JsonHandler.stringify(resultMap);
        }
        resultMap.put("result", -1);
        return JsonHandler.stringify(resultMap);
    }

    @Override
    public int updateDocument(String params) {
        Map<String, Object> paramsMap = JsonHandler.parse(params, Map.class);
        paramsMap.put("lastEdit", DateUtils.getFormatDate());
        return mapper.updateDocument(paramsMap);
    }

    @Override
    public int publishDocument(String params) {
        Map<String, Object> paramsMap = JsonHandler.parse(params, Map.class);
        paramsMap.put("lastEdit", DateUtils.getFormatDate());
        return mapper.publishDocument(paramsMap);
    }

    @Override
    public int withdrawDocument(String id) {
        return mapper.withdrawDocument(id);
    }


}
