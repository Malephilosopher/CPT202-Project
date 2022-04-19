package com.xjtlu.cpt202.cpt202Project.mapper;

import com.xjtlu.cpt202.cpt202Project.entity.Blog;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BlogMapper {
    /**
     * 根据限制的展示数量找到blog列表
     * @param limit
     * @return
     */
    List<Blog> selectBlogs(int limit);

    /**
     * 插入帖子
     * @param blog
     * @return
     */
    int insertBlog(Blog blog);

    /**
     * 根据帖子id查找帖子
     * @param blog_id
     * @return
     */
    Blog selectBlogById(int blog_id);

    /**
     * 删除帖子
     * @param blog_id
     * @return
     */
    int deleteBlog(int blog_id);


/**
     * 根据关键字找到blog列表
     * @param keyword
     * @return
     */
    List<Blog> selectBlogByKeywords(String keyword);

}