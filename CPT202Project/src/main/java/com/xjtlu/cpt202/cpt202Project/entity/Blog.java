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
    private String title; //文章标题
    private int author_id;
    private String post_time;
    private String edit_time;
    private int num_like;
    private int num_favorite;
    private String content;
    private int view_num;
    private String tag;  //文章标签


    public void set_post_time(long post_time) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(post_time);
        this.post_time = formatter.format(date);
    }


    public void set_edit_time(long edit_time) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(edit_time);
        this.edit_time = formatter.format(date);
    }


}
