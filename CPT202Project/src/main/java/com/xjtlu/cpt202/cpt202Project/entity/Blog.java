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
    private int num_fav;
    private String content;
    private String description;
    //the number of users who have viewed the blog
    private int num_view;
    private String tag;


    public void setPost_time(long post_time) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(post_time);
        this.post_time = formatter.format(date);
    }

    public String getPost_time() {
        return post_time;
    }

    public void setEdit_time(long edit_time) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(edit_time);
        this.edit_time = formatter.format(date);
    }

    public String getEdit_time() {
        return edit_time;
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
                ", num_favorite=" + num_fav +
                ", content='" + content + '\'' +
                ", view_num=" + num_view +
                ", tag='" + tag + '\'' +
                '}';
    }
}
