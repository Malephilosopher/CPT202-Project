package com.xjtlu.cpt202.modao.service;


import com.xjtlu.cpt202.modao.Entity.Comment;

import java.util.List;

public interface CommentService {
    public List<Comment> listCommentByBlogId(Long BlogId);


    public int addComment(Comment comment);
    public int deleteComment(Comment comment);

}

