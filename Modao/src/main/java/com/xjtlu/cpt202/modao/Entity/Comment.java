package com.xjtlu.cpt202.modao.Entity;

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

    private Long blogId;

    private Long authorId;

    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creatTime;

    @OneToMany(mappedBy = "parentComment")
    private List<Comment> replyComments = new ArrayList<>();

    @ManyToOne
    private Comment parentComment;


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

    public void setContent(String commentMsg) { this.content = content == null ? null : content.trim();
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
        sb.append("Hash = ").append(hashCode());
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
