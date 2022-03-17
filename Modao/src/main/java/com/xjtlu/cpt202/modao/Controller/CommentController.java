package com.xjtlu.cpt202.modao.Controller;
import  com.xjtlu.cpt202.modao.Entity.Comment;

import com.xjtlu.cpt202.modao.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long newId, Model model){
        model.addAttribute("comments",commentService.listCommentByBlogId(newId));
        return "new::commentList";
    }
    @PostMapping("/comments")
    public String post(Comment comment){
          Long blogId = comment.getBlogId();
//        comment.setBlogId(newService.getBlog(BlogId));
//        User user = (User) session.getAttribute("user");
//        if(user!=null){
//            comment.setAvatar(avatar);
//        }else{
//            comment.setAvatar(avatar);
//        }
        commentService.saveComment(comment);
//        System.out.println(commentService.saveComment(comment));
        return "redirect:/comments/"+blogId;
    }

}
