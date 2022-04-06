package com.xjtlu.cpt202.cpt202Project.service;


import com.xjtlu.cpt202.cpt202Project.entity.Comment;

import java.util.List;

public interface CommentService {
    public List<Comment> listCommentByBlogId(int BlogId);
    public int addComment(Comment comment);
    public int deleteComment(Comment comment);

}

