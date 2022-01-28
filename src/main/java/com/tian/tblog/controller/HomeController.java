package com.tian.tblog.controller;

import com.tian.tblog.service.DocumentService;
import com.tian.tblog.utils.JsonHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/tblog/home")
public class HomeController {
    // last article, hot project
    @Autowired
    DocumentService service;

    @GetMapping("/getLastArticle/{day}")
    public String lastArticle(@PathVariable("day") int day){
        return JsonHandler.stringify(service.getLastDocument(day));
    }
}
