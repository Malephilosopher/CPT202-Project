package com.xjtlu.cpt202.cpt202Project.entity;

import com.xjtlu.cpt202.cpt202Project.service.Impl.UserServiceImpl;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;



@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

//    注册的时候要用到的
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String gender;
    private String grade;
    private String major;
//    the time that the user registered
//    private String born_time;

    //the number of blogs the user likes
    private Integer like_blog;
    //the number of comments the user have commented
    private Integer comment_num;
    //the user's favorite blogs
    private Integer fav_blog;
    //the number of fans of the user
    private Integer num_fan;

//    private List<Integer> thumbUpArticle;













}
