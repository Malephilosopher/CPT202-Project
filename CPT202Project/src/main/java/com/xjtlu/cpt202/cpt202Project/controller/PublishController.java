package com.xjtlu.cpt202.cpt202Project.controller;

import com.alibaba.fastjson.JSON;
import com.xjtlu.cpt202.cpt202Project.entity.Blog;
import com.xjtlu.cpt202.cpt202Project.entity.Comment;
import com.xjtlu.cpt202.cpt202Project.entity.Result;
import com.xjtlu.cpt202.cpt202Project.entity.User;
import com.xjtlu.cpt202.cpt202Project.mapper.BlogMapper;
import com.xjtlu.cpt202.cpt202Project.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

//帖子发布
@RestController
public class PublishController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    @ResponseBody
    public String addBlog(String title,String discription, String content,String username,int like,int userid) {
        User user = userService.getUser(userid);
        if (user == null) {
            return "用户未登录";
        }
        //帖子上传数据库
        Blog blog = new Blog();
        blog.setId(user.getId());
        blog.setTitle(title);
        blog.setContent(content);
        blog.setPost_time(System.currentTimeMillis());
        BlogService.addBlog(blog);

        // 报错的情况之后处理
        return "发布成功";
    }

    @RequestMapping(path = "/getPage", method = RequestMethod.GET)
    public String BlogPage(@PathVariable("BlogId") int BlogId, Model model) {
        // 帖子
        Blog blog = BlogService.findBlogById(BlogId);
        model.addAttribute("blog", blog);
        // 作者
        User user = userService.getUser(blog.getAuthor_id());
        model.addAttribute("user", user);

//        // 评论分页信息
//        page.setLimit(5);
//        page.setPath("/discuss/detail/" + BlogId);
//        page.setRows(blog.getComment_count());

        // 评论: 给帖子的评论
        // 回复: 给评论的评论
        // 评论列表
        List<Comment> commentList = commentService.listCommentByBlogId(blog.getId()); //long改int
        // 评论VO列表
        List<Map<String, Object>> commentVoList = new ArrayList<>();
        if (commentList != null) {
            for (Comment comment : commentList) {
                // 评论VO
                Map<String, Object> commentVo = new HashMap<>();
                // 评论
                commentVo.put("comment", comment);
                // 作者
                commentVo.put("user", userService.getUser(comment.getAuthorId())); //long改int

                // 回复列表
                List<Comment> replyList = commentService.listCommentByBlogId(comment.getCommentId());
                // 回复VO列表
                List<Map<String, Object>> replyVoList = new ArrayList<>();
                if (replyList != null) {
                    for (Comment reply_comment : replyList) {
                        Map<String, Object> replyVo = new HashMap<>();
                        // 回复
                        replyVo.put("reply_comment", reply_comment);
                        // 作者
                        replyVo.put("user", userService.getUser(reply_comment.getAuthorId())); //long改int
                        // 回复目标
                        User target = reply_comment.getParentCommentId() == 0 ? null : userService.getUser(reply_comment.getParentCommentId());
                        replyVo.put("target", target);

                        replyVoList.add(replyVo);
                    }
                }
                commentVo.put("replys", replyVoList);

                commentVoList.add(commentVo);
            }
        }

        model.addAttribute("comments", commentVoList);

        return "get success";
    }
}

