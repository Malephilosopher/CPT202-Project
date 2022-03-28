package com.xjtlu.cpt202.cpt202Project.controller;
import com.alibaba.fastjson.JSON;
import  com.xjtlu.cpt202.cpt202Project.entity.Comment;

import com.xjtlu.cpt202.cpt202Project.service.CommentService;
import com.xjtlu.cpt202.cpt202Project.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;
    //展示所有的评论
    @GetMapping("/comments/{blogId}")
    public String listComments(@PathVariable("blogId") Long blogId, Model model) {
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("comments", comments);
        return "blog :: commentList";
    }
//添加一条评论
    @PostMapping("/comment")
    public String addComment(Comment comment, String userName, Long id) {
   //     Long blogId = addComment.getBlogId();
        comment.setUserName(userName);
        int result = commentService.addComment(comment);
     //这两行看后期需要
    //    List<Comment> comments = commentService.listCommentByBlogId(blogId);
    //       model.addAttribute("comments", comments);
        if(result == 1){
            return JSON.toJSONString(Result.success("Comment added successfully"));
        }else{
            return JSON.toJSONString(Result.fail("Comment add failed"));
        }
    }
    //    删除评论
    @GetMapping("/comment/{blogId}/delete")
    public String delete(@PathVariable Long blogId,Comment comment,  Model model){
        commentService.deleteComment(comment);
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("comments", comments);
        return "blog";
    }
}
