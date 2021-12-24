package com.tian.tblog.service;

import com.tian.tblog.bean.Document;

import java.util.List;
import java.util.Map;

public interface DocumentService {
    List<Document> documentList();

    Document queryDocument(String id);

    Document queryDocumentByTitle(String title);

    List<Document> unpublishedDocList();

    int deleteDocument(String id);

    String addDocument(Map<String, Object> params);

    int updateDocument(Map<String, Object> params);

    int publishDocument(Map<String, Object> params);

    int withdrawDocument(String id);
}
