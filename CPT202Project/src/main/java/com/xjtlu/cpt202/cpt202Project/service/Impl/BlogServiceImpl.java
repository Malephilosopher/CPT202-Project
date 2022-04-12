package com.xjtlu.cpt202.cpt202Project.service.Impl;

import com.xjtlu.cpt202.cpt202Project.entity.Blog;
import com.xjtlu.cpt202.cpt202Project.mapper.BlogMapper;
import com.xjtlu.cpt202.cpt202Project.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private  BlogMapper blogMapper;

    @Override
    public List<Blog> findBlogs(int userId, int offset, int limit) {
        return blogMapper.selectBlogs(userId, offset, limit);
    }
    @Override
    public int findBlogRows(int userId) {
        return blogMapper.selectBlogRows(userId);
    }

    @Override
    public  int addBlog(Blog blog) {
        if (blog == null) {
            throw new IllegalArgumentException("参数不能为空!");
        }
        // 转义HTML标记
        blog.setTitle(HtmlUtils.htmlEscape(blog.getTitle()));
        blog.setDescription(HtmlUtils.htmlEscape(blog.getDescription()));
        blog.setContent(HtmlUtils.htmlEscape(blog.getContent()));
        return blogMapper.insertBlog(blog);
    }
    @Override
    public  Blog findBlogById(int id) {
        return blogMapper.selectBlogById(id);
    }
    @Override
    public int deleteBlog(int blog_id){
        return blogMapper.deleteBlog(blog_id);

    }

}
