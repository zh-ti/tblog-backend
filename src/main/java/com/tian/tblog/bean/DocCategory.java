package com.tian.tblog.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocCategory {
    private String id;
    private String name;
    private String createDatetime;
    private int likes;
    private int browses;
    private int docAmount;

    public DocCategory(String id, String name, String createDatetime){
        this.id = id;
        this.name = name;
        this.createDatetime = createDatetime;
    }
}
