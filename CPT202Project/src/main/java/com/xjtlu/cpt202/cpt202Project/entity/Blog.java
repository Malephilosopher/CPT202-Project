package com.xjtlu.cpt202.cpt202Project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Blog {

    private int id;
    private String title;
    private int author_id;
    //the time that the blog was posted
    private String post_time;
    //the time that the blog was edited
    private String edit_time;
    //the number of users who have liked the blog
    private int num_like;
    //the number of users who have favorited the blog
    private int num_favorite;
    private String content;
    //the number of users who have viewed the blog
    private int view_num;
    private String tag;
    private String description;


    public void setPost_time(long post_time) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(post_time);
        this.post_time = formatter.format(date);
    }



    public void setEdit_time(long edit_time) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(edit_time);
        this.edit_time = formatter.format(date);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author_id=" + author_id +
                ", post_time='" + post_time + '\'' +
                ", edit_time='" + edit_time + '\'' +
                ", num_like=" + num_like +
                ", num_favorite=" + num_favorite +
                ", content='" + content + '\'' +
                ", view_num=" + view_num +
                ", tag='" + tag + '\'' +
                '}';
    }
}
