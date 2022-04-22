package com.xjtlu.cpt202.cpt202Project.entity;

import lombok.*;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
@ToString
public class Blog {

    private Integer id;
    private String title;
    private Integer author_id;
    //the time that the blog was posted
    private Timestamp post_time;
    //the time that the blog was edited
    private Timestamp edit_time;
    //the number of users who have liked the blog
    private Integer num_like;
    //the number of users who have favorited the blog
    private Integer num_fav;
    private String content;
    private String description;
    //the number of users who have viewed the blog
    private Integer num_view;
    //whether the blog is liked by current user
    private String is_like;
    //whether the blog is collected by current user
    private String is_collected;

}
