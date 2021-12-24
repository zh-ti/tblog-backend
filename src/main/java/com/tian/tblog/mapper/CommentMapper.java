package com.tian.tblog.mapper;

import com.tian.tblog.bean.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface CommentMapper {

    List<Comment> getCommentList();

    List<Comment> getCommentListByDoc(String docId);

    int addComment(Comment comment);

    int deleteComment(String id);

    int deleteCommentByDoc(String docId);

}
