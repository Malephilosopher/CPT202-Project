package com.xjtlu.cpt202.cpt202Project.service;

import java.util.List;

import com.xjtlu.cpt202.cpt202Project.entity.User;

public interface UserService {

//    储存新用户
    int saveUser(User u);

//    获取用户信息
    User getUser(int id);

//    通过用户名获得用户id
    long getUserId(String username);

//    获取用户点赞过的所有点赞信息
    List<Integer> getThumbUpId(int id);

//    点赞功能
    int thumbUp(int user_id, int blog_id);

//    获取点赞数量
    Integer getThumbNum(int blog_id);

//    获取收藏信息
    List<Integer> getCollectId(int user_id);

//    收藏功能
    int Collect(int user_id, int blog_id);

//    获取收藏数量
    Integer getCollectNum(int blog_id);

//    获取用户创作的文章id
    List<Integer> getCreateId(int id);

//    获取和修改用户具体信息
//    change and get method of [username, password, email, gender, grade, major].


    //    username
    String changeUserName(int id, String newName);
    String getUserName(int id);

    //    password
    String changeUserPassword(int id, String newPassword);
    String getUserPassword(int id);

    //    email
    String changeUserEmail(int id, String newEmail);
    String getUserEmail(int id);

    //    gender
    String changeUserGender(int id, String newGender);
    String getUserGender(int id);

    //    grade
    String changeUserGrade(int id, String newGrade);
    String getUserGrade(int id);





//    获取用户粉丝id
//    List<User> getFanNumber(int id);


}
