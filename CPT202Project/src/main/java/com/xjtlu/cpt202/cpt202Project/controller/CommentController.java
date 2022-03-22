package com.xjtlu.cpt202.cpt202Project.controller;
import  com.xjtlu.cpt202.cpt202Project.entity.Comment;

import com.xjtlu.cpt202.cpt202Project.service.CommentService;
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
    public String comments(@PathVariable("blogId") Long blogId,Model model) {
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("comments", comments);
        return "blog :: commentList";
    }
//添加一条评论
    @PostMapping("/comments")
    public String add(Comment comment, Model model) {
        Long blogId = comment.getBlogId();
        commentService.addComment(comment);
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("comments", comments);
        return  "blog :: commentList";
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
