package com.tian.tblog.mapper;

import com.tian.tblog.bean.Article;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ArticleMapper {

    List<Article> queryArticleList();

    List<Article> queryPublishedArticle();

    List<Article> queryUnpublishedArticleList();

    Article queryArticle(String id);

    Article queryArticleByTitle(String name);

    int insertArticle(Article article);

    int deleteArticle(String id);

    int updateArticle(Article article);

    int withdrawArticle(String id);

    int publishArticle(Article article);

    List<Article> queryLastArticle(String date);
}
