package com.tian.tblog.service.impl;

import com.tian.tblog.bean.Article;
import com.tian.tblog.bean.ResponseEntity;
import com.tian.tblog.bean.ResultStatus;
import com.tian.tblog.mapper.ArticleMapper;
import com.tian.tblog.service.ArticleService;
import com.tian.tblog.utils.DateUtils;
import com.tian.tblog.utils.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper mapper;

    @Override
    public ResponseEntity getArticleList() {
        return new ResponseEntity(mapper.queryArticleList());
    }

    @Override
    public ResponseEntity getPublishedArticle() {
        return new ResponseEntity(mapper.queryPublishedArticle());
    }

    @Override
    public ResponseEntity getArticle(String id) {
        return new ResponseEntity(mapper.queryArticle(id));
    }

    @Override
    public ResponseEntity getArticleByTitle(String title) {
        return new ResponseEntity(mapper.queryArticleByTitle(title));
    }

    @Override
    public ResponseEntity getUnpublishedArticleList() {
        return new ResponseEntity(mapper.queryUnpublishedArticleList());
    }

    @Override
    public ResponseEntity deleteArticle(String id) {
        return new ResponseEntity(mapper.deleteArticle(id));
    }

    @Override
    public ResponseEntity addArticle(Article article){
        ResponseEntity responseEntity = new ResponseEntity();
        if(mapper.queryArticleByTitle(article.getTitle()) == null){
            String uuid = UUIDGenerator.getShortUUID();
            article.setId(uuid);
            article.setLastEdit(DateUtils.getNowFormatDate());
            mapper.insertArticle(article);
            responseEntity.setData(uuid);
        }else{
            responseEntity.setResultStatus(new ResultStatus("文章标题“"+article.getTitle()+"”已存在"));
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity updateArticle(Article article) {
        ResponseEntity responseEntity = new ResponseEntity();
        if(mapper.queryArticle(article.getId()) != null){
            Article rArticle;
            if((rArticle = mapper.queryArticleByTitle(article.getTitle())) != null && !article.equals(rArticle)){
                responseEntity.setResultStatus(new ResultStatus("文章标题“"+article.getTitle()+"”已存在"));
            }else{
                article.setLastEdit(DateUtils.getNowFormatDate());
                int result = mapper.updateArticle(article);
                responseEntity.setData(result > 0);
            }
        }else{
            responseEntity.setResultStatus(new ResultStatus("未找到指定的文章"));
        }

        return responseEntity;
    }

    @Override
    public ResponseEntity publishArticle(String id) {
        ResponseEntity responseEntity = new ResponseEntity();
        Article article;
        if((article = mapper.queryArticle(id)) != null){
            article.setState(1);
            int result = mapper.updateArticle(article);
            System.out.println(result);
            responseEntity.setData(result > 0);
        }else{
            responseEntity.setResultStatus(new ResultStatus("未找到该文章"));
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity withdrawArticle(String id) {
        ResponseEntity responseEntity = new ResponseEntity();
        Article article;
        if((article = mapper.queryArticle(id)) == null){
            responseEntity.setResultStatus(new ResultStatus("未找到该文章"));
        }else{
            article.setState(0);
            int result = mapper.updateArticle(article);
            responseEntity.setData(result > 0);
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity getLastArticle(Integer day){
        LocalDateTime now = LocalDateTime.now();
        day = (day == null) ? 3 : day;
        LocalDateTime localDateTime = now.minusDays(day);
        return new ResponseEntity(mapper.queryLastArticle(DateUtils.format(localDateTime)));
    }
}
