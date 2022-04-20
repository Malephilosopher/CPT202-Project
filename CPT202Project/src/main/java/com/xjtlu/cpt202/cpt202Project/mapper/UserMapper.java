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

    //    增加新用户
    //put同时向缓存里添加key为id值的user对象
    @CachePut(key = "#p0.id")
    Integer addUser(User u);

    //    根据id在数据库里查询用户
    //    两种连接mysql的方法：
    //优先从缓存获取
    @Cacheable(key = "#p0")
    User findUserById(int id);
//    User findById(@Param("id") int userId);

    //    更新用户信息
    int updateUser(User u);

    Integer getThumbNum(int blog_id);

    //    获取用户点赞过的所有文章
    List<Integer> getThumbUpId(int id);

    //    删除用户
    int deleteUser(User u);

    //    根据用户名查询用户id
    Object getUserId(String username);

    //    插入点赞记录
    int addLike(int user_id, int blog_id);

    //    取消点赞记录
    int cancelLike(int user_id, int blog_id);

    //    获取用户收藏的文章id
    List<Integer> getCollectId(int user_id);

    //    获取用户创作的文章id
    List<Integer> getCreateId(int id);

    //    增加文章点赞数量
    int addLikeNum(int blog_id);

    //    减少文章点赞数量
    int reduceLikeNum(int blog_id);

    //    插入收藏记录
    int addCollect(int user_id, int blog_id);

    //    取消收藏记录
    int cancelCollect(int user_id, int blog_id);

    //    增加文章收藏数量
    int addCollectNum(int blog_id);

    //    减少文章收藏数量
    int reduceCollectNum(int blog_id);

    //    获取收藏数量
    Integer getCollectNum(int blog_id);


//    List<User> findAllFans(int id);




}
