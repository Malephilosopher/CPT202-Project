package com.xjtlu.cpt202.cpt202Project.mapper;

import com.xjtlu.cpt202.cpt202Project.entity.Blog;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BlogMapper {
    List<Blog> selectBlogs(int userId, int offset, int limit);
    //Param用于给参数起别名，并且在<if>里面使用，则必须起别名，动态拼凑条件时，只有一个参数
    int selectBlogRows(@Param("userId") int userId);

    int insertBlog(Blog blog);

    Blog selectBlogById(int id);

    int updateCommentCount(int id, int commentCount);
}


