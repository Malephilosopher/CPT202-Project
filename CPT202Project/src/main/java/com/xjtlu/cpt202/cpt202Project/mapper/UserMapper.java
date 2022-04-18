package com.xjtlu.cpt202.cpt202Project.mapper;

import com.xjtlu.cpt202.cpt202Project.entity.Comment;
import com.xjtlu.cpt202.cpt202Project.entity.User;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Mapper
@CacheConfig(cacheNames = "user")
public interface UserMapper {

    //    查询全部用户
    List<User> getUserList();

    //    查询全部用户id
    List<Integer> getUserIdList();

    //    用户点赞的文章
    List<Integer> getThumbUpId(int id);

    //    增加新用户
    //put同时向缓存里添加key为id值的user对象
    @CachePut(key = "#p0.id")
    User addUser(User u);

    //    根据id在数据库里查询用户
    //    两种连接mysql的方法：
    //优先从缓存获取
    @Cacheable(key = "#p0")
    User findUserById(int id);
//    User findById(@Param("id") int userId);

    void updateUser(User u);

    int getThumbNum(int blog_id);

    //    删除用户
    int deleteUser(User u);

    //    根据用户名查询用户id
    Object getUserId(String username);

    //    插入点赞记录
    int addLike(int user_id, int blog_id);

    int cancelLike(int user_id, int blog_id);

    List<Integer> getCollectId(int user_id);

    List<Integer> getCreateId(int id);

//    List<User> findAllFans(int id);




}
