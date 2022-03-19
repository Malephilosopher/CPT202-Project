package com.xjtlu.cpt202.cpt202Project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@PropertySource(value = "classpath:application.properties")
public class User {

    @Value("${username}")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
