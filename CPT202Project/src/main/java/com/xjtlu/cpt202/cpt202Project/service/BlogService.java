package com.xjtlu.cpt202.cpt202Project.service;

import com.xjtlu.cpt202.cpt202Project.entity.Blog;
import com.xjtlu.cpt202.cpt202Project.mapper.BlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Service
public class BlogService {
    @Autowired
    private BlogMapper blogMapper;



    public List<Blog> findBlogs(int userId, int offset, int limit) {
        return blogMapper.selectBlogs(userId, offset, limit);
    }

    public int findBlogRows(int userId) {
        return blogMapper.selectBlogRows(userId);
    }

    public int addBlog(Blog blog) {
        if (blog == null) {
            throw new IllegalArgumentException("参数不能为空!");
        }

        // 转义HTML标记
        blog.setTitle(HtmlUtils.htmlEscape(blog.getTitle()));
        blog.setContent(HtmlUtils.htmlEscape(blog.getContent()));

        return blogMapper.insertBlog(blog);
    }

    public Blog findBlogById(int id) {
        return blogMapper.selectBlogById(id);
    }

    public int updateCommentCount(int id, int commentCount) {
        return blogMapper.updateCommentCount(id, commentCount);
    }

}
