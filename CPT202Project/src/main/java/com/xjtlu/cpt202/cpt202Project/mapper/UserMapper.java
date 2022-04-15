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

    //    用户点赞的文章
    List<Integer> getThumbUp(int id);

    //    增加新用户
    void addUser(User u);

    //    根据id在数据库里查询用户
    //    两种连接mysql的方法：

    User findUserById(int id);
//    User findById(@Param("id") int userId);

    void updateUser(User u);


    //    删除用户
    void deleteUser(User u);

    Object getUserId(String username);


//    List<User> findAllFans(int id);


}
