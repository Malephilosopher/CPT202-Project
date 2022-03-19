package com.xjtlu.cpt202.cpt202Project.mapper;
import  com.xjtlu.cpt202.cpt202Project.entity.Comment;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

public interface CommentMapper extends BaseMapper<Comment> {
    //按时间顺序返回博客下所有父评论为null的评论
    List<Comment> findParentQuery(Long biogId) ;
    //返回某一父评论下的所有子评论
    List<Comment> findChildrenQuery(Long commentId);

    Comment findById(Long parentCommentId);
}
