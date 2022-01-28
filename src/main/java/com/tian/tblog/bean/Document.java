package com.tian.tblog.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Document {
    private String id;
    private String title;
    private String brief;
    private String content;
    private String categoryId;
    private String category;
    private String publishDatetime;
    private String lastEdit;
    private int state;
    private int browses;
    private int likes;
    private int comments;
    private String cover;
    private String origin;

    public Document(String id, String title, String categoryId, String publishDatetime){
        this.id = id;
        this.title = title;
        this.categoryId = categoryId;
        this.publishDatetime = publishDatetime;
    }
}
