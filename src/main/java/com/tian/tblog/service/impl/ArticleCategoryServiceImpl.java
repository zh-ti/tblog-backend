package com.tian.tblog.service.impl;

import com.tian.tblog.bean.ResponseEntity;
import com.tian.tblog.bean.ResultStatus;
import com.tian.tblog.mapper.ArticleCategoryMapper;
import com.tian.tblog.service.ArticleCategoryService;
import com.tian.tblog.utils.DateUtils;
import com.tian.tblog.utils.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleCategoryServiceImpl implements ArticleCategoryService {

    @Autowired
    private ArticleCategoryMapper mapper;

    @Override
    public ResponseEntity getArticleCategoryList(){
        return new ResponseEntity(mapper.queryArticleCateGoryList());
    }

    @Override
    public ResponseEntity getArticleCategory(String id) {
        return new ResponseEntity(mapper.queryArticleCategory(id));
    }

    @Override
    public ResponseEntity addArticleCategory(String name) {
        ResponseEntity responseEntity = new ResponseEntity();
        if(mapper.queryArticleCategoryByName(name) == null){
            String id = UUIDGenerator.getShortUUID();
            String createTime = DateUtils.getNowFormatDate();
            int result = mapper.insertArticleCategory(id, name, createTime);
            responseEntity.setData(result > 0);
        }else{
            responseEntity.setResultStatus(new ResultStatus("该分类已存在"));
        }

        return responseEntity;
    }

    @Override
    public ResponseEntity updateArticleCategory(String id, String newName) {
        ResponseEntity responseEntity = new ResponseEntity();
        if(mapper.queryArticleCategoryByName(newName) == null) {
            int result = mapper.updateArticleCategory(id, newName);
            responseEntity.setData(result > 0);
        }else{
            responseEntity.setResultStatus(new ResultStatus("该分类已存在"));
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity deleteArticleCategory(String id) {
        ResponseEntity responseEntity = new ResponseEntity();
        if(mapper.queryArticleCategory(id) != null){
            if(mapper.queryArticleAmount(id) > 0) {
                responseEntity.setResultStatus(new ResultStatus("请先删除该分类下的所有文章"));
            }else{
                int result = mapper.deleteArticleCategory(id);
                responseEntity.setData(result > 0);
            }
        }else{
            responseEntity.setResultStatus(new ResultStatus("未找到该分类"));
        }

        return responseEntity;
    }

}
