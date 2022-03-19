package com.xjtlu.cpt202.cpt202Project.service;
import  com.xjtlu.cpt202.cpt202Project.entity.Comment;

import com.xjtlu.cpt202.cpt202Project.mapper.CommentMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> listCommentByBlogId(Long BlogId) {
        //获取博客下的所有最初父评论
        List<Comment> comments = commentMapper.findParentQuery(BlogId);
        //返回所有配对好的父评论与子评论
        return findChildrenComment(comments);
    }
    private List<Comment> commentsView = new ArrayList<>();
    private List<Comment> findChildrenComment(List<Comment> comments){
        for(Comment comment:comments){
            Comment c = new Comment();
            BeanUtils.copyProperties(comment,c);
            c.setReplyComments(commentMapper.findChildrenQuery(c.getCommentId()));
            //迭代寻找每一个子回复的子回复
            recursivelyFindReplys(c);
            commentsView.add(c);
        }
        return commentsView;
    }

    private void recursivelyFindReplys(Comment comment){
        List<Comment> replys = comment.getReplyComments();
        if(!replys.isEmpty()){
            for(Comment reply:replys){
                reply.setReplyComments(commentMapper.findChildrenQuery(reply.getCommentId()));
                if(!reply.getReplyComments().isEmpty()) {
                    //再次迭代寻找子回复的子回复
                    recursivelyFindReplys(reply);
                }
            }

    }
    }
    @Override
    public int addComment(Comment comment) {
        return commentMapper.insert(comment);
    }

    @Override
    public int deleteComment(Comment comment){
        return commentMapper.delete(comment);
    }

}
