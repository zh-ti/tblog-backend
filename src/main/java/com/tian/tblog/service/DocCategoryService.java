package com.tian.tblog.service;

import com.tian.tblog.bean.DocCategory;

import java.util.List;

public interface DocCategoryService {
    List<DocCategory> docCategoryList();

    DocCategory queryDocCategory(String id);

    int addDocCategory(String name);

    int updateDocCategory(String id, String newName);

    int deleteDocCategory(String id);
}
