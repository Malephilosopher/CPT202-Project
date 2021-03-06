package com.xjtlu.cpt202.cpt202Project.service.Impl;
import  com.xjtlu.cpt202.cpt202Project.entity.Comment;

import com.xjtlu.cpt202.cpt202Project.mapper.CommentMapper;
import com.xjtlu.cpt202.cpt202Project.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();

    @Override
    //以二层嵌套的结构展示某blog的所有评论
    public List<Comment> listCommentByBlogId(int blogId) {
        //获取博客下的所有最初父评论(根节点)
        List<Comment> comments = commentMapper.findParentQuery(blogId,0);
        for(Comment comment : comments){
            int id = comment.getId();
            //            查询出根评论下所有子评论
            List<Comment> childComments = commentMapper.findChildrenQuery(id);

            combineChildren(blogId, childComments);
            //重新设置根节点评论的子评论
            comment.setReplyComments(tempReplys);
            //清空临时子评论集合
            tempReplys = new ArrayList<>();
        }
        return comments;
    }
    //将多层嵌套转变为二层嵌套结构
    private void combineChildren(int blogId, List<Comment> childComments){
        //        判断是否有一级子评论
        if(childComments.size() > 0) {
//                循环找出子评论的id
            for (Comment childComment : childComments) {
                tempReplys.add(childComment);
                int childId = childComment.getId();
//                    查询出子二级评论
                recursively(blogId, childId);
            }
        }
    }

    private void recursively(int blogId, int childId){
//        根据子一级评论的id找到子二级评论
        List<Comment> replayComments = commentMapper.findChildrenQuery(childId);

        if(replayComments.size() > 0){
            for(Comment replayComment : replayComments){
                int replayId = replayComment.getId();
                tempReplys.add(replayComment);
                //递归找出所有层级的子评论
                recursively(blogId,replayId);
            }
        }
    }

    @Override
    //创建并添加一条评论
    public int addComment(Comment comment) {
//        int parentCommentId = comment.getParentCommentId();
//        //若父评论为空则返回默认值为-1
//        if(parentCommentId != 0){
//
//        }
        return commentMapper.insert(comment);
    }

    @Override
    public int deleteComment(int commentId){
       return  commentMapper.deleteComment(commentId);

    }
    @Override
    public  int getCommentNumber(int blogId){
        return commentMapper.getCommentCount(blogId);
    }

}
