package com.xjtlu.cpt202.cpt202Project.mapper;

import com.xjtlu.cpt202.cpt202Project.entity.Comment;
import com.xjtlu.cpt202.cpt202Project.entity.User;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Mapper
public interface UserMapper {

    //    查询全部用户
    List<User> getUserList();

    //    查询全部用户id
    List<Integer> getUserIdList();

    //    根据id在数据库里查询用户
    User findUserById(int id);

    //    根据用户名查询用户id
    Object getUserId(String username);

    //    增加新用户
    int addUser(User u);
    //    更新用户信息
    int updateUser(User u);

    //    删除用户
    int deleteUser(User u);

    //    获取用户点赞过的所有文章
    List<Integer> getThumbUpId(int id);

    //    插入点赞记录
    int addLike(int user_id, int blog_id);

    //    取消点赞记录
    int cancelLike(int user_id, int blog_id);

    //    获取用户点赞过的文章id
    List<Integer> getCollectId(int user_id);

    //    获取用户创作的文章id
    List<Integer> getCreateId(int id);

//    List<User> findAllFans(int id);


}
