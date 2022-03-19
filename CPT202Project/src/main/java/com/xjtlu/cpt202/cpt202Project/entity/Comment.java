package com.xjtlu.cpt202.cpt202Project.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    //所在帖子id
    private Long blogId;

    //作者
    private Long authorId;

    //内容
    private String content;

    //发布时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date creatTime;

    //回复
    @OneToMany(mappedBy = "parentComment")
    private List<Comment> replyComments = new ArrayList<>();

    //回复的评论
    @ManyToOne
    private Comment parentComment;

    public Comment() {
    }

    public Comment(Long blog, Long id, Long author, Date time, String content) {
        this.blogId = blog;
        this.commentId= id;
        this.authorId = author;
        this.creatTime = time;
        this.content = content;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if(content !=null){
        if(content.trim().isEmpty()){
            this.content =null;
        }else{
            this.content = content;
        }
    }
    }

    public Date getCreatTime() {
        return creatTime;
    }


    public void setCreateTime(Date date) {
        this.creatTime = date;
    }

    public List<Comment> getReplyComments(){
            return replyComments;
    }

    public void setReplyComments(List<Comment> tempComent){
        replyComments = tempComent;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parent) {
        parentComment = parent;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append(", commentId=").append(commentId);
        sb.append(", blogId=").append(blogId);
        sb.append(", authorId=").append(authorId);
        sb.append(", commentMsg=").append(content);
        sb.append(", creatTime=").append(creatTime);
        sb.append(", replyComent=").append(replyComments);
        sb.append("]");
        return sb.toString();
    }


}
