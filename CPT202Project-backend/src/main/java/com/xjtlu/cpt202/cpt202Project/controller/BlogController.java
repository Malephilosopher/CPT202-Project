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

    /**
     * create blog
     * @param information
     * @return code + message
     */
    @PostMapping("/create")
    public String addBlog(@RequestBody String information) {
        Blog info = JSON.parseObject(information, Blog.class);
        User user = userService.getUser(info.getAuthor_id());
        if (user == null) {
            return JSON.toJSONString(Result.create(300,"User not found"));
        }
        //Upload blog to the database
        Blog blog = new Blog();
        blog.setAuthor_id(user.getId());
        blog.setTitle(info.getTitle());
        blog.setDescription(info.getDescription());
        blog.setContent(info.getContent());
        blog.setPost_time(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        blog.setEdit_time(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        blog.setNum_like(0);
        blog.setNum_fav(0);
        blog.setNum_view(0);
        if(blog.getTitle() == null || blog.getDescription() == null || blog.getContent() == null){
            return JSON.toJSONString(Result.create(300,"Please complete title,description and content"));
        }
        blogService.addBlog(blog);
        // publish success
        return JSON.toJSONString(Result.create(200,"create blog successfully"));
    }

    //get article details
    /**
     * article details
     * @param id
     * @return code + message + information of blog and user
     */
    @GetMapping("/getArticle")
    public String BlogPage(@RequestParam(value = "user_id") int user_id, @RequestParam(value = "post_id") int id) {
        // blog information
        Blog blog = blogService.findBlogById(user_id, id);
        if (blog == null) {
            return JSON.toJSONString(Result.create(300,"Can't find blog"));
        }
        //author information
        User user = userService.getUser(blog.getAuthor_id());
        return JSON.toJSONString(Result.create(200,"Get Blog information success", List.of(blog, user)));

    }

    /**
     * get homepage
     * @param amount
     * @return code + message + blog list
     */
    @GetMapping("/getContent")
    public String getHomePage(@RequestParam(value = "amount") int amount, @RequestParam(value = "user_id") int user_id){
        if (amount == 0){
            return JSON.toJSONString(Result.create(300,"amount can't be null"));
        }
        List<Blog> blog_list = blogService.findBlogs(user_id, amount);
        if (blog_list==null){
            JSON.toJSONString(Result.fail("Get blog_list failed"));
        }
        return JSON.toJSONString(Result.create(200,"Get Blog_list information success", blog_list));
    }

    /**
     * searching function
     * @param information
     * @return code + message + data : blog_list
     */
    @PostMapping("/keyWords")
    public String QueryBlogs(@RequestBody String information){
        //unpack and get keyword
        JSONObject jsonObject = JSONObject.parseObject(information);
        int user_id = jsonObject.getInteger("user_id");
        String keyword = jsonObject.getString("keyword");
        if(keyword==null){
            return JSON.toJSONString(Result.create(300,"keyword can't be null"));
        }
        List<Blog> blog_list = blogService.searchBlogs(user_id, keyword);
        if (blog_list==null){
            JSON.toJSONString(Result.fail("Get blog_list failed"));
        }
        return JSON.toJSONString(Result.create(200,"Get Blog_list information success", blog_list));
    }
    
    /**
     * edit function
     * @param information
     * @return code + message + data : new_blog
     */
    @PostMapping("/editBlog")
    public String editBlog(@RequestBody String information){
        //unpack and get keyword
        Blog info = JSON.parseObject(information, Blog.class);
        //Update blog to the database
        Blog blog = new Blog();
        blog.setId(info.getId());
        blog.setTitle(info.getTitle());
        blog.setDescription(info.getDescription());
        blog.setContent(info.getContent());
        blog.setEdit_time(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        if(blog.getTitle()==null||blog.getDescription()==null||blog.getContent()==null){
            return JSON.toJSONString(Result.create(300,"Please complete title,description and content"));
        }
        blogService.editBlog(blog);
        // edit success
        return blogService.editBlog(blog)==1?JSON.toJSONString(Result.create(200,"edit blog successfully")):JSON.toJSONString(Result.create(300,"edit blog failed"));
    }
}

