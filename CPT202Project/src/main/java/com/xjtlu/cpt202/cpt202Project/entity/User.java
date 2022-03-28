package com.xjtlu.cpt202.cpt202Project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
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
    private int like_blog;
    private int fav_blog;
    private Date born_time;
    private int num_fan;

    private List<User> likeUser;




}
