package com.xjtlu.cpt202.cpt202Project.service.Impl;

import cn.hutool.core.util.BooleanUtil;
import com.xjtlu.cpt202.cpt202Project.entity.Blog;
import com.xjtlu.cpt202.cpt202Project.mapper.BlogMapper;
import com.xjtlu.cpt202.cpt202Project.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public List<Blog> findBlogs(int user_id, int limit) {
        List<Blog> list = blogMapper.selectBlogs(limit);
        for (Blog blog: list) {
            isBlogLiked(user_id, blog);
            isBlogCollected(user_id, blog);
        }
        return list;
    }

    @Override
    public  int addBlog(Blog blog) {
        if (blog == null) {
            throw new IllegalArgumentException("参数不能为空!");
        }
//        // 转义HTML标记
//        blog.setTitle(HtmlUtils.htmlEscape(blog.getTitle()));
//        blog.setDescription(HtmlUtils.htmlEscape(blog.getDescription()));
//        blog.setContent(HtmlUtils.htmlEscape(blog.getContent()));
        return blogMapper.insertBlog(blog);
    }

    @Override
    public Blog findBlogById(int user_id, int id) {
        Blog blog =  blogMapper.selectBlogById(id);
        isBlogLiked(user_id, blog);
        isBlogCollected(user_id, blog);
        return blog;
    }

    @Override
    public int deleteBlog(int blog_id){
        return blogMapper.deleteBlog(blog_id);

    }
    @Override
    public List<Blog> searchBlogs(int user_id, String keyword){
        List<Blog> list = blogMapper.selectBlogByKeywords(keyword);
        for (Blog blog: list) {
            isBlogLiked(user_id, blog);
            isBlogCollected(user_id, blog);
        }
        return list;
    }

    /**
     * 判断当前登陆用户是否已经点赞
     * @param user_id
     * @param blog
     */
    public void isBlogLiked(int user_id, Blog blog){
        String key = "blog:liked:" + blog.getId();
        Boolean isMember = redisTemplate.opsForSet().isMember(key, String.valueOf(user_id));
        blog.setIs_like(BooleanUtil.isTrue(isMember) ? "true" : "false");
    }

    /**
     * 判断当前登陆用户是否已经收藏
     * @param user_id
     * @param blog
     */
    public void isBlogCollected(int user_id, Blog blog){
        String key = "blog:collected:" + blog.getId();
        Boolean isMember = redisTemplate.opsForSet().isMember(key, String.valueOf(user_id));
        blog.setIs_collected(BooleanUtil.isTrue(isMember) ? "true" : "false");
    }

}
