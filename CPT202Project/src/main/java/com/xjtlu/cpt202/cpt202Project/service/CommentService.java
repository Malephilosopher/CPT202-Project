package com.xjtlu.cpt202.cpt202Project.service;


import com.xjtlu.cpt202.cpt202Project.entity.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> listCommentByBlogId(int BlogId);
    int addComment(Comment comment);
    int deleteComment(Comment comment);

}

