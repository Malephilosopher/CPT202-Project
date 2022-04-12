package com.xjtlu.cpt202.cpt202Project.entity;

import com.xjtlu.cpt202.cpt202Project.service.Impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;



@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Autowired
    UserServiceImpl userService;
//    注册的时候要用到的
    private int id;
    private String username;
    private String password;
    private String email;
    private String gender;
    private String grade;
    private String major;
//    the time that the user registered
//    private Date born_time;

    //the number of blogs the user likes
    private int like_blog;
    //the number of comments the user have commented
    private int comment_num;
    //the user's favorite blogs
    private int fav_blog;
    //the number of fans of the user
    private int num_fan;

//    private List<Integer> thumbUpArticle;













}
