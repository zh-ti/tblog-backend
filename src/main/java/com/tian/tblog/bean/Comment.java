package com.tian.tblog.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private String id;
    private String nickname;
    private String refCommentId;
    private String email;
    private String comment;
    private String commentDatetime;
}
