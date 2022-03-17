package com.xjtlu.cpt202.modao.Mapper;
import  com.xjtlu.cpt202.modao.Entity.Comment;
import org.apache.ibatis.annotations.Lang;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);
    Lang findById(Lang Id);
    List<Comment> findByParentIdAnd(Long blogId, Sort sort);
}
