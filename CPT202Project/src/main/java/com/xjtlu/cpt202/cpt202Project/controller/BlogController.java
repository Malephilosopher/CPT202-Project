package com.xjtlu.cpt202.cpt202Project.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xjtlu.cpt202.cpt202Project.entity.*;
import com.xjtlu.cpt202.cpt202Project.service.BlogService;
import com.xjtlu.cpt202.cpt202Project.service.CommentService;
import com.xjtlu.cpt202.cpt202Project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Clock;
import java.util.*;


@RestController
@Slf4j
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;

    //帖子创作
    /**
     * 帖子创作
     * @param information
     * @return code + message
     */
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
        blog.setTitle(info.getTitle());
        blog.setDescription(info.getDescription());
        blog.setContent(info.getContent());
        blog.setPost_time(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        blog.setEdit_time(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        blog.setNum_like(0);
        blog.setNum_view(0);
        blogService.addBlog(blog);
        if(blog.getTitle()==null||blog.getDescription()==null||blog.getContent()==null){
            return JSON.toJSONString(Result.create(300,"Please complete title,description and content"));
        }

        // 无错返回
        return JSON.toJSONString(Result.create(200,"create blog successfully"));
    }

    //文章详情

    /**
     * 文章详情
     * @param id
     * @return code + message + information of blog and user
     */
    @GetMapping("/getArticle")
    public String BlogPage(@RequestParam(value = "postId") int id) {
        // 帖子信息
        Blog blog = blogService.findBlogById(id);
        System.out.println(blog);
        if (blog == null) {
            return JSON.toJSONString(Result.create(300,"Can't find blog"));
        }

        // 作者信息
        User user = userService.getUser(blog.getAuthor_id());
        return JSON.toJSONString(Result.create(200,"Get Blog information success", List.of(blog, user)));

    }

    /**
     * 获取主页面
     * @param amount
     * @return code + message + blog list
     */
    @GetMapping("/getContent")
    public String getHomePage(@RequestParam(value = "amount") int amount){
        if (amount == 0){
            return JSON.toJSONString(Result.create(300,"amount can't be null"));
        }
        List<Blog> blog_list = blogService.findBlogs(amount);
        if (blog_list==null){
            JSON.toJSONString(Result.fail("Get blog_list failed"));
        }
        return JSON.toJSONString(Result.create(200,"Get Blog_list information success", blog_list));
    }




    /**
     * 搜索功能
     * @param information
     * @return code + message + data : blog_list
     */
    @PostMapping("/keyWords")
    public String QueryBlogs(@RequestBody String information){
        JSONObject jsonObject = JSONObject.parseObject(information);
        // 获取到key为shoppingCartItemList的值
        String keyword = jsonObject.getString("keyword");
        if(keyword==null){
            return JSON.toJSONString(Result.create(300,"keyword can't be null"));
        }
        List<Blog> blog_list = blogService.searchBlogs(keyword);
        if (blog_list==null){
            JSON.toJSONString(Result.fail("Get blog_list failed"));
        }
        return JSON.toJSONString(Result.create(200,"Get Blog_list information success", blog_list));
    }







}
