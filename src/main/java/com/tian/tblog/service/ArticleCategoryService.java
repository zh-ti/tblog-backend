package com.tian.tblog.service;

import com.tian.tblog.bean.ArticleCategory;
import com.tian.tblog.bean.ResponseEntity;

import java.util.List;

public interface ArticleCategoryService {
    ResponseEntity getArticleCategoryList();

    ResponseEntity getArticleCategory(String id);

    ResponseEntity addArticleCategory(String name);

    ResponseEntity updateArticleCategory(String id, String newName);

    ResponseEntity deleteArticleCategory(String id);
}
