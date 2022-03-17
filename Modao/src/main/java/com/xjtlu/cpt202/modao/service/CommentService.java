package com.xjtlu.cpt202.modao.service;

import com.xjtlu.cpt202.modao.Entity.Comment;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CommentService {
    public List<Comment> listCommentByBlogId(Long BlogId);
    Comment saveComment(Comment comment);

}

