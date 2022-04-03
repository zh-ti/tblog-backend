package com.tian.tblog.mapper;

import com.tian.tblog.bean.ArticleCategory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ArticleCategoryMapper {

    List<ArticleCategory> queryArticleCateGoryList();

    ArticleCategory queryArticleCategory(String id);

    ArticleCategory queryArticleCategoryByName(String name);

    int insertArticleCategory(String id, String name, String createDatetime);

    int updateArticleCategory(String id, String newName);

    int deleteArticleCategory(String id);

    int queryArticleAmount(String id);
}
