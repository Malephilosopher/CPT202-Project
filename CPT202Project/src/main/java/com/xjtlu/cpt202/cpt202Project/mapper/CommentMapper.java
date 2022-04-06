package com.xjtlu.cpt202.cpt202Project.mapper;
import  com.xjtlu.cpt202.cpt202Project.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper  {

    /**
     * 通过博客id和父评论id查找所有子评论 并按照时间倒序排序
     *
     * @param blogId
     * @param parentCommentId
     * @return List<Comment>
     */
    List<Comment> findParentQuery(@Param("blogId") int blogId, @Param("parentCommentId")int parentCommentId) ;

    /**
     * 通过父评论id查找所有子评论 并按照时间倒序排序
     * @param commentId
     * @return List<Comment>
     */
    List<Comment> findChildrenQuery(@Param("commentId") int commentId);

    /**
     * 通过父评论id查找所有子评论 并按照时间倒序排序
     * @param commentId
     * @return Comment
     */
    Comment findById(@Param("CommentId") int commentId);

    /**
     * 删除评论
     * @param commentId
     * @return int：1表成功删除
     */
    int deleteComment(int commentId);

    /**
     * 插入评论
     * @param comment
     * @return int：1表成功删除
     */
    int insert(Comment comment);
}
