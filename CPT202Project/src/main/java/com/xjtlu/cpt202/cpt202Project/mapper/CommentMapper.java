package com.xjtlu.cpt202.cpt202Project.mapper;
import  com.xjtlu.cpt202.cpt202Project.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper  {

    //按创建时间倒序返回博客下所有父评论为null的评论,即所有根节点
    List<Comment> findParentQuery(@Param("blogId") Long blogId, @Param("parentCommentId")Long parentCommentId) ;
    //返回某一父评论下的所有子评论
    List<Comment> findChildrenQuery(@Param("commentId") Long commentId);
    //通过评论ID找到某一评论，并返回comment对象
    Comment findById(@Param("CommentId") Long CommentId);
    //删除评论
    int deleteComment(Long commentId);
    // 插入一条评论
    int insert(Comment comment);
}
