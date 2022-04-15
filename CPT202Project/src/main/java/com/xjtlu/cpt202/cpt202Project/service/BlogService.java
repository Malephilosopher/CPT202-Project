package com.xjtlu.cpt202.cpt202Project.service;

import com.xjtlu.cpt202.cpt202Project.entity.Blog;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

public interface BlogService {

    List<Blog> findBlogs(int offset, int limit);

    int findBlogRows(int userId);

    int addBlog(Blog blog);

    Blog findBlogById(int id);

    int deleteBlog(int blog_id);
}
