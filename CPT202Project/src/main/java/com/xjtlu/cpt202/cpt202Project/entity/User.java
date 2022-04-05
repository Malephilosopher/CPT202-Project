package com.xjtlu.cpt202.cpt202Project.entity;

import com.xjtlu.cpt202.cpt202Project.service.Impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int id;
    private String username;
    private String password;
    private String email;
    private boolean gender;
    private int grade;
    private String major;
    //the number of blogs the user likes
    private int like_blog;
    //the number of comments the user have commented
    private int comment_num;
    //the user's favorite blogs
    private int fav_blog;
    //the time that the user registered
    private Date born_time;
    //the number of fans of the user
    private int num_fan;
    //the blog ids the user has liked
    private List<Integer> thumbUpArticle;
    //the user ids that the user like
    private List<User> likeUser;







}
