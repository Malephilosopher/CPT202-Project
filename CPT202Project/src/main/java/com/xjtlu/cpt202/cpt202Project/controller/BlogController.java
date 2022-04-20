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
     * @return
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

    @PostMapping("/thumbArticleTwo")
    public String thumbUp2(@RequestBody String blog) {
        JSONObject object = JSONObject.parseObject(blog);
        int blog_id = object.getIntValue("blog_id");
        return JSON.toJSONString(Result.create(200, "get thumb up number", userService.getThumbNum(blog_id)));
    }

    /**
     * 点赞功能
     * @param user_like（user_id + blog_id）
     * @return code + message(thumb up or not thumb up)
     * 若无点赞记录时在user_like表中加入一条点赞记录；
     * 若点赞记录已存在则取消点赞。
     */
    @PostMapping("/thumbArticleOne")
    public String thumbup(@RequestBody String user_like) {
        JSONObject object = JSONObject.parseObject(user_like);
        int user_id = object.getIntValue("user_id");
        int blog_id = object.getIntValue("blog_id");
        List<Integer> userLike = userService.getThumbUpId(user_id);
        if (userLike.contains(blog_id)) {
            log.info("点赞记录已存在， 取消点赞");
            userService.notThumbUp(user_id, blog_id);
            return JSON.toJSONString(Result.create(200, "not thumb up"));
        } else {
            int success = userService.thumbUp(user_id, blog_id);
            if (success == 1) {
                log.info("点赞成功");
                return JSON.toJSONString(Result.create(200, "thumb up successfully"));
            } else {
                return JSON.toJSONString(Result.create(300, "fail to thumb up"));
            }
        }
    }

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
