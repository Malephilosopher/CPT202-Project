package com.xjtlu.cpt202.modao.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Post {
    private int id;
    private String title;
    private int author_id;
    private String post_time;
    private String edit_time;
    private int num_like;
    private int num_favorite;
    private String content;
    private int view_num;
    private String tag;

    public String get_post_time() {
        return post_time;
    }

    public void set_post_time(long post_time) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(post_time);
        this.post_time = formatter.format(date);
    }

    public String get_edit_time() {
        return edit_time;
    }

    public void set_edit_time(long edit_time) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(edit_time);
        this.edit_time = formatter.format(date);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public int getView_count() {
        return view_num;
    }

    public void setView_count(int view_num) {
        this.view_num = view_num;
    }

    public int getLike_count() {
        return num_like;
    }

    public void setLike_count(int num_like) {
        this.num_like = num_like;
    }

    public int getFavorite_count() {
        return num_favorite;
    }

    public void setFavorite_count(int num_favorite) {
        this.num_favorite = num_favorite;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

}
