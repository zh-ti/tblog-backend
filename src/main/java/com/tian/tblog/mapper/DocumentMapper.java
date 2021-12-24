package com.tian.tblog.mapper;

import com.tian.tblog.bean.Document;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface DocumentMapper {

    List<Document> getDocumentList();

    List<Document> getUnpublishedDocList();

    Document getDocument(String id);

    Document getDocumentByTitle(String name);

    int addDocument(Map<String, Object> params);

    int deleteDocument(String id);

    int updateDocument(Map<String, Object> params);

    int withdrawDocument(String id);

    int publishDocument(Map<String, Object> params);
}
