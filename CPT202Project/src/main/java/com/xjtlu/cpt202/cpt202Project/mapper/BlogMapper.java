package com.xjtlu.cpt202.cpt202Project.mapper;

import com.xjtlu.cpt202.cpt202Project.entity.Blog;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BlogMapper {
    /**
     * 根据用户id，限制的展示数量找到blog列表
     * @param userId
     * @param offset
     * @param limit
     * @return
     */
    List<Blog> selectBlogs(int offset, int limit);

    /**
     * 根据用户id查找帖子
     * @param userId
     * @return
     */
    //Param用于给参数起别名，并且在<if>里面使用，则必须起别名，动态拼凑条件时，只有一个参数
    int selectBlogRows(@Param("userId") int userId);

    /**
     * 插入帖子
     * @param blog
     * @return
     */
    int insertBlog(Blog blog);

    /**
     * 根据帖子id查找帖子
     * @param id
     * @return
     */
    Blog selectBlogById(int id);

    /**
     * 删除帖子
     * @param blog_id
     * @return
     */
    int deleteBlog(int blog_id);
}


