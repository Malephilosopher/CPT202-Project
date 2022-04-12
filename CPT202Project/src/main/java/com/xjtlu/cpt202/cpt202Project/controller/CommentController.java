package com.xjtlu.cpt202.cpt202Project.controller;
import com.alibaba.fastjson.JSON;
import  com.xjtlu.cpt202.cpt202Project.entity.Comment;
import com.xjtlu.cpt202.cpt202Project.mapper.CommentMapper;
import com.xjtlu.cpt202.cpt202Project.service.CommentService;
import com.xjtlu.cpt202.cpt202Project.entity.Result;
import com.xjtlu.cpt202.cpt202Project.service.Impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    /**
     * 展示博客下的所有评论
     * @param blogId
     * @return 展示成功：code:200, message:Comment list successfully, data: comments
     * @return 展示失败：code:300, message:blogId is null
     */
    @GetMapping("/comments")
    public String listComments(@RequestParam (name = "blogId") int blogId) {
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        if (comments.isEmpty()||comments== null) {
            return JSON.toJSONString(Result.create(300,"blogId is not found"));
        }
        return JSON.toJSONString(Result.create(200,"Comment list successfully", comments));
    }


    /**
     * 添加一条评论
     * @param comment
     * \\@param userId
     * \\@param id
     * @return 添加成功：code:200, message:comment added successfully
     * @return 添加失败：code:300, message:comment add failed
     */
    @PostMapping("/comment")
    public String addComment(@RequestBody String comment) {
        Comment comm = JSON.parseObject(comment, Comment.class);
   //     Long blogId = addComment.getBlogId()
        //     comm.setAuthorId(userId);
        int result = commentService.addComment(comm);
        if("".equals(comm.getContent())||comm.getContent()==null){
            return JSON.toJSONString(Result.fail("Comment add failed"));
        }
        if(result ==1){
            return JSON.toJSONString(Result.success("Comment add successfully"));
        }
        else{
            return JSON.toJSONString(Result.fail("Comment add failed"));
        }

     //这两行看后期需要
    //    List<Comment> comments = commentService.listCommentByBlogId(blogId);
    //       model.addAttribute("comments", comments);

    }

    /**
     *
     * @param blogId
     * @param commentId
     * @return 删除成功：code:200, message:comment delete successfully
     */

    @GetMapping("/comment/{blogId}/delete")
    public String delete(@PathVariable int blogId,@RequestParam(value = "id") int commentId) {
        int result = commentService.deleteComment(commentId);
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        if (result == 1) {
            return JSON.toJSONString(Result.success("Comment delete successfully"));
        } else {
            return JSON.toJSONString(Result.fail("Comment delete fail"));
        }
    }
}
