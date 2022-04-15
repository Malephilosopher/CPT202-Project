package com.xjtlu.cpt202.cpt202Project.controller;

import com.alibaba.fastjson.JSON;
import com.xjtlu.cpt202.cpt202Project.entity.*;
import com.xjtlu.cpt202.cpt202Project.service.BlogService;
import com.xjtlu.cpt202.cpt202Project.service.CommentService;
import com.xjtlu.cpt202.cpt202Project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;

    //帖子创作
    @PostMapping("/create")
    public String addBlog(@RequestBody String information) {
        Blog info = JSON.parseObject(information, Blog.class);
        User user = userService.getUser(info.getAuthor_id());
        if (user == null) {
            return JSON.toJSONString(Result.create(300,"User not logged in"));
        }
        //帖子上传数据库
        Blog blog = new Blog();
        blog.setAuthor_id(user.getId());
        //blog.setTitle(info.getTitle());
        blog.setDescription(info.getDescription());
        blog.setContent(info.getContent());
        blog.setPost_time(System.currentTimeMillis());
        blog.setEdit_time(System.currentTimeMillis());
        blog.setNum_like(info.getNum_like());
        blog.setNum_view(info.getNum_view());
        blogService.addBlog(blog);

        // 无错返回
        return JSON.toJSONString(Result.create(200,"create blog successfully"));
    }

    //文章详情
    @GetMapping("/getPage")
    public String BlogPage(@RequestParam(value = "id") int id) {
        if (id == 0) {
            return JSON.toJSONString(Result.create(300,"Can't find blog"));
        }
        // 帖子信息
        Blog blog = blogService.findBlogById(id);
        // 作者信息
        User user = userService.getUser(blog.getAuthor_id());
        return JSON.toJSONString(Result.create(200,"Get Blog information success", blog))+
        JSON.toJSONString(Result.create(200,"Get author information success", user));
    }




}
