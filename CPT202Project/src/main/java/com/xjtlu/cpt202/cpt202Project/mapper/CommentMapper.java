package com.xjtlu.cpt202.cpt202Project.mapper;
import  com.xjtlu.cpt202.cpt202Project.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper extends BaseMapper<Comment> {
    //按时间顺序返回博客下所有父评论为null的评论,即所有根节点
    List<Comment> findParentQuery(Long biogId) ;
    //返回某一父评论下的所有子评论
    List<Comment> findChildrenQuery(Long commentId);
    //通过评论ID找到某一评论，并返回comment对象
    Comment findById(Long parentCommentId);
}
