package com.tian.tblog.controller;

import com.tian.tblog.bean.ResponseEntity;
import com.tian.tblog.service.ArticleCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articleCategory")
public class ArticleCategoryController {

    @Autowired
    ArticleCategoryService service;

    @GetMapping("/getCategoryList")
    public ResponseEntity categoryList(){
        return service.getArticleCategoryList();
    }

    @RequestMapping("/getCategory/{id}")
    public ResponseEntity getCategory(@PathVariable("id") String id){
        return service.getArticleCategory(id);
    }

    @RequestMapping("/addCategory/{name}")
    public ResponseEntity addCategory(@PathVariable("name") String name){
        System.out.println(name);
        return service.addArticleCategory(name);
    }

    @RequestMapping("/updateCategory/{id}/{newName}")
    public ResponseEntity updateCategory(@PathVariable("id") String id, @PathVariable("newName") String newName){
        return service.updateArticleCategory(id, newName);
    }

    @PostMapping("/deleteCategory/{id}")
    public ResponseEntity deleteCategory(@PathVariable("id") String id){
        System.out.println(id);
        return service.deleteArticleCategory(id);
    }
}
