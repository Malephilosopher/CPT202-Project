package com.xjtlu.cpt202.cpt202Project.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import  com.xjtlu.cpt202.cpt202Project.entity.Comment;
import com.xjtlu.cpt202.cpt202Project.entity.Result;
import com.xjtlu.cpt202.cpt202Project.service.Impl.CommentServiceImpl;
import com.xjtlu.cpt202.cpt202Project.service.Impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;
    @Autowired
    private UserServiceImpl userService;
    /**
     * 展示博客下的所有评论
     * @param blogId
     * @return 展示成功：code:200, message:Comment list successfully, data: comments
     * @return 展示失败：code:300, message:Comments are not found
     */
    @GetMapping("/getComment")
    public String listComments(@RequestParam (name = "blogId") int blogId) {
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        if (comments.isEmpty()||comments== null) {
            log.info("获取"+blogId +"博客评论成功");
            return JSON.toJSONString(Result.create(300,"Comments are not found"));
        }
        return JSON.toJSONString(Result.create(200,"Comment list successfully", comments));
    }


    /**
     * 添加一条评论
     * @param comment
     * @return 添加成功：code:200, message:comment added successfully
     * @return 添加失败：code:300, message:comment add failed
     */
    @PostMapping("/sendComment")
    public String addComment(@RequestBody String comment) {
        JSONObject js =  JSONObject.parseObject(comment);

        Comment comm = new Comment();
        comm.setBlog_id(js.getIntValue("blogId"));
        comm.setAuthor_id(js.getIntValue("authorId"));
        comm.setContent(js.getString("comment"));
        comm.setUserName(userService.getUserName(js.getIntValue("authorId")));
        comm.setPost_time(LocalDateTime.now());
        //暂时只做一级评论
        comm.setParent_comment_id(0);
        if("".equals(comm.getContent())||comm.getContent()==null){
            return JSON.toJSONString(Result.fail("Comment add failed"));
        }
        int result = commentService.addComment(comm);
        int id = comm.getId();
        if(result ==1){
            log.info("添加"+comm.getContent() +"添加评论成功");
            return JSON.toJSONString(Result.success("Comment add successfully",id));
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

    /**
     *
     * @param blogId
     * @return 获取成功：Result code:200, message:Get comment count data: comment num
     */
    @GetMapping("/getCommentNum/{blogId}")
    public String getNum(@PathVariable int blogId) {
        int num  =commentService.getCommentNumber(blogId);
        return  JSON.toJSONString(Result.create(200,"Get comment count",num));
    }
}
