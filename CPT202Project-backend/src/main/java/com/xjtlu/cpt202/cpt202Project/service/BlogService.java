package com.xjtlu.cpt202.cpt202Project.service;

import com.xjtlu.cpt202.cpt202Project.entity.Blog;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;


public interface BlogService {
    //get a time-sorted blog_list based on the limit amount
    List<Blog> findBlogs(int user_id, int limit);

    //add blog info into database
    int addBlog(Blog blog);

    //find blog by blog_id
    Blog findBlogById(int user_id, int id);

    //delete blog by blog_id
    int deleteBlog(int blog_id);

    //get a time-sorted blog_list based on the keyword
    List<Blog> searchBlogs(int user_id, String keyword);

    //search in redis whether the blog is liked by current user
    void isBlogLiked(int blog_id, Blog blog);

    //search in redis whether the blog is collected by current user
    void isBlogCollected(int blog_id, Blog blog);
}
