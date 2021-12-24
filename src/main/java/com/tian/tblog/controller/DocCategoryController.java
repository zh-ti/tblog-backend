package com.tian.tblog.controller;

import com.tian.tblog.service.DocCategoryService;
import com.tian.tblog.utils.JsonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/tblog/docCategory")
public class DocCategoryController {

    @Autowired
    DocCategoryService service;

    @GetMapping("/getCategoryList")
    public String docCategoryList(){
        return JsonHandler.stringify(service.docCategoryList());
    }

    @RequestMapping("/queryCategory/{id}")
    public String queryCategory(@PathVariable("id") String id){
        return JsonHandler.stringify(service.queryDocCategory(id));
    }

    @RequestMapping("/addCategory/{name}")
    public String addCategory(@PathVariable("name") String name){
        return JsonHandler.stringify(service.addDocCategory(name));
    }

    @RequestMapping("/updateCategory/{id}/{newName}")
    public String updateCategoryList(@PathVariable("id") String id, @PathVariable("newName") String newName){
        return JsonHandler.stringify(service.updateDocCategory(id, newName));
    }

    @RequestMapping("/deleteCategory/{id}")
    public String deleteCategory(@PathVariable("id") String id){
        return JsonHandler.stringify(service.deleteDocCategory(id));
    }
}
