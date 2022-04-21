package com.xjtlu.cpt202.cpt202Project.service;

import com.xjtlu.cpt202.cpt202Project.entity.Blog;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;


public interface BlogService {
    //get a time-sorted blog_list based on the limit amount
    List<Blog> findBlogs(int limit);

    //add blog info into database
    int addBlog(Blog blog);

    //find blog by blog_id
    Blog findBlogById(int id);

    //delete blog by blog_id
    int deleteBlog(int blog_id);

    //get a time-sorted blog_list based on the keyword
    List<Blog> searchBlogs(String keyword);
}
