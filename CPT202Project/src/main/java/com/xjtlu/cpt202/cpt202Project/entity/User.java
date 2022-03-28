package com.xjtlu.cpt202.cpt202Project.entity;

import com.xjtlu.cpt202.cpt202Project.service.Impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Autowired
    UserServiceImpl userService;

    private int id;
    private String username;
    private String password;
    private String email;
    private boolean gender;
    private int grade;
    private String major;
    private int like_blog;
    private int fav_blog;
    private Date born_time;
    private int num_fan;
    private List<Integer> thumbUpArticle;

    private List<User> likeUser;

    {
        thumbUpArticle = getThumbUp();
    }

    private List<Integer> getThumbUp(){
        List<Integer> thumbUpArticles = userService.getThumbUp(this.id);
        return thumbUpArticle;
    }




}
