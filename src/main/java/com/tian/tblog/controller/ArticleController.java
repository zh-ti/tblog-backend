package com.tian.tblog.controller;

import com.tian.tblog.bean.Article;
import com.tian.tblog.bean.ResponseEntity;
import com.tian.tblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService service;

    @RequestMapping("/getArticleList")
    public ResponseEntity getArticleList(){
        return service.getArticleList();
    }

    @RequestMapping("/getPublishedArticle")
    public ResponseEntity getPublishedArticle(){
        return service.getPublishedArticle();
    }

    @GetMapping("/getArticle/{id}")
    public ResponseEntity queryArticle(@PathVariable("id") String id){
        return service.getArticle(id);
    }

    @GetMapping("/deleteArticle/{id}")
    public ResponseEntity deleteArticle(@PathVariable("id") String id){
        return service.deleteArticle(id);
    }

    @PostMapping("/addArticle")
    public ResponseEntity addArticle(@RequestBody Article article) {
        return service.addArticle(article);
    }

    @PostMapping("/updateArticle")
    public ResponseEntity updateArticle(Article article) {
        return service.updateArticle(article);
    }

    @GetMapping("/getUnpublishedArticleList")
    public ResponseEntity getUnpublishedDocList(){
        return service.getUnpublishedArticleList();
    }

    @GetMapping("/withdrawArticle/{id}")
    public ResponseEntity withdrawArticle(@PathVariable("id") String id){
        return service.withdrawArticle(id);
    }

    @PostMapping("/publishArticle/{id}")
    public ResponseEntity publishArticle(@PathVariable("id") String id) {
        return service.publishArticle(id);
    }

    @GetMapping("/getLastArticle")
    public ResponseEntity lastArticle(@Nullable @RequestParam("day") Integer day){
        return service.getLastArticle(day);
    }
}
