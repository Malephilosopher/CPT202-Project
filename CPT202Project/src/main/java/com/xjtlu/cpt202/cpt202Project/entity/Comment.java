package com.xjtlu.cpt202.cpt202Project.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private Long blogId;

    private Long authorId;

    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creatTime;

    //replies
    @OneToMany(mappedBy = "parentComment")
    private List<Comment> replyComments = new ArrayList<>();

    //parent
    @ManyToOne
    private Comment parentComment;

    public void setContent(String content) {
        if(content !=null){
        if(content.trim().isEmpty()){
            this.content =null;
        }else{
            this.content = content;
        }
    }
    }


}
