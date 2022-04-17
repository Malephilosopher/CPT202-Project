package com.xjtlu.cpt202.cpt202Project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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


    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author_id=" + author_id +
                ", post_time='" + post_time + '\'' +
                ", edit_time='" + edit_time + '\'' +
                ", num_like=" + num_like +
                ", num_favorite=" + num_fav +
                ", content='" + content + '\'' +
                ", view_num=" + num_view +
                '}';
    }
}
