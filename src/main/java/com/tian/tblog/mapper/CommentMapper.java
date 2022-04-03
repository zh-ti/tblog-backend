package com.tian.tblog.mapper;

import com.tian.tblog.bean.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {
    // 查询所有的评论
    List<Comment> queryCommentList();
    // 查询指定文章下的评论
    List<Comment> queryCommentListByArticle(String articleId);
    // 插入一条评论
    int insertComment(Comment comment);
    // 删除一条评论
    int deleteComment(String id);
    // 删除指定文章下的评论
    int deleteCommentByArticle(String docId);

}
