package com.xjtlu.cpt202.modao.service;
import  com.xjtlu.cpt202.modao.Entity.Comment;

import com.xjtlu.cpt202.modao.Mapper.CommentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> listCommentByBlogId(Long BlogId) {
        Sort sort = Sort.by("createTime");
        //获取博客下的所有最初父评论
        List<Comment> comments =commentRepository.findByBlogIdAndParentCommentNull(BlogId,sort);
        return getEachComment(comments);
    }

    private List<Comment> getEachComment (List<Comment> comments){
        List<Comment> commentsView = new ArrayList<>();
        for(Comment comment:comments){
            Comment c = new Comment();
            BeanUtils.copyProperties(comment,c);
            commentsView.add(c);
        }
        //合并评论的所有子评论到第一级评论集合中
        combineChildren(commentsView);
        return commentsView;
    }

    private void combineChildren(List<Comment> comments){
        for(Comment comment:comments){
            List<Comment> replys1 = comment.getReplyComments();
            for(Comment reply1:replys1){
                //循环迭代，找出子代,存放在临时tempReplys中
                recursively(reply1);
            }
            comment.setReplyComments(tempReplys);

            //清除临时存放区
            tempReplys = new ArrayList<>();
        }
    }

    private List<Comment> tempReplys = new ArrayList<>();
    private void recursively(Comment comment){
        tempReplys.add(comment);//顶节点
        if(comment.getReplyComments().size()>0){
            List<Comment> replys = comment.getReplyComments();
            for(Comment reply:replys){
                tempReplys.add(reply);
                if(reply.getReplyComments().size()>0){
                    recursively(reply);
                }
            }
        }
    }
    @Override
    public Comment saveComment(Comment comment) {
        Long parentCommentId = comment.getParentComment().getCommentId();
        if(parentCommentId!=-1){
            comment.setParentComment(commentRepository.findById(parentCommentId).orElse(null));
        }
        else{
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        return commentRepository.save(comment);
    }

}
