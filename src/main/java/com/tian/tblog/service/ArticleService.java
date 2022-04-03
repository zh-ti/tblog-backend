package com.tian.tblog.service;

import com.tian.tblog.bean.Article;
import com.tian.tblog.bean.ResponseEntity;

import java.util.List;

public interface ArticleService {
    ResponseEntity getArticleList();

    ResponseEntity getPublishedArticle();

    ResponseEntity getArticle(String id);

    ResponseEntity getArticleByTitle(String title);

    ResponseEntity getUnpublishedArticleList();

    ResponseEntity deleteArticle(String id);

    ResponseEntity addArticle(Article article);

    ResponseEntity updateArticle(Article article);

    ResponseEntity publishArticle(String id);

    ResponseEntity withdrawArticle(String id);

    ResponseEntity getLastArticle(Integer day);
}
