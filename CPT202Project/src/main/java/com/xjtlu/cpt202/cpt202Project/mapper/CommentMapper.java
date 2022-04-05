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
    List<Comment> findParentQuery(@Param("blogId") Long blogId, @Param("parentCommentId")Long parentCommentId) ;

    /**
     * 通过父评论id查找所有子评论 并按照时间倒序排序
     * @param commentId
     * @return List<Comment>
     */
    List<Comment> findChildrenQuery(@Param("commentId") Long commentId);

    /**
     * 通过父评论id查找所有子评论 并按照时间倒序排序
     * @param commentId
     * @return Comment
     */
    Comment findById(@Param("CommentId") Long commentId);

    /**
     * 删除评论
     * @param commentId
     * @return int：1表成功删除
     */
    int deleteComment(Long commentId);

    /**
     * 插入评论
     * @param comment
     * @return int：1表成功删除
     */
    int insert(Comment comment);
}
