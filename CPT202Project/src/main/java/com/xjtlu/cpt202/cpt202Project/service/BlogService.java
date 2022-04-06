package com.xjtlu.cpt202.cpt202Project.service;

import com.xjtlu.cpt202.cpt202Project.entity.Blog;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

public interface BlogService {

    public List<Blog> findBlogs(int userId, int offset, int limit);

    public int findBlogRows(int userId);

    public int addBlog(Blog blog);

    public Blog findBlogById(int id);

    public int deleteBlog(int blog_id);
}
