package com.tian.tblog.service;

import com.tian.tblog.bean.Document;

import java.util.List;

public interface DocumentService {
    List<Document> documentList();

    List<Document> publishedDocument();

    Document queryDocument(String id);

    Document queryDocumentByTitle(String title);

    List<Document> unpublishedDocList();

    int deleteDocument(String id);

    String addDocument(String params);

    int updateDocument(String params);

    int publishDocument(String params);

    int withdrawDocument(String id);

    List<Document> getLastDocument(int day);
}
