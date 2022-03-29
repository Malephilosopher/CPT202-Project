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
    private String post_time;
    private String edit_time;
    private int num_like;
    private int num_favorite;
    private String content;
    private int view_num;
    private String tag;

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

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public void setPost_time(long post_time) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(post_time);
        this.post_time = formatter.format(date);
    }

    public String getPost_time() {
        return post_time;
    }


    public void setEdit_time(long edit_time) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(edit_time);
        this.edit_time = formatter.format(date);
    }

    public String getEdit_time() {
        return edit_time;
    }

    public int getNum_like() {
        return num_like;
    }


    public void setNum_like(int num_like) {
        this.num_like = num_like;
    }

    public int getNum_favorite() {
        return num_favorite;
    }

    public void setNum_favorite(int num_favorite) {
        this.num_favorite = num_favorite;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getView_num() {
        return view_num;
    }

    public void setView_num(int view_num) {
        this.view_num = view_num;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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
