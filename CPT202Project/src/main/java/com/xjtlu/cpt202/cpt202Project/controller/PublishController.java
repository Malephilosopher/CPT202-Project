package com.xjtlu.cpt202.cpt202Project.controller;

import com.xjtlu.cpt202.cpt202Project.cache.TagCache;
import com.xjtlu.cpt202.cpt202Project.dto.TagDto;
import com.xjtlu.cpt202.cpt202Project.entity.Blog;
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
import java.util.List;


//问题发布
@Controller
public class PublishController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BlogMapper postMapper;

    @GetMapping("/publish")
    public String publish(Model model) {
        //标签组
        TagCache tagCache=new TagCache();
        List<TagDto> tags = tagCache.gettags();
        model.addAttribute("tags",tags);
        return "publish";
    }

    //发贴
    @PostMapping("/publish")
    public String publishpost(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("tag") String tag,
            @RequestParam(value = "id", defaultValue = "-1") int id,
            HttpServletRequest request,
            Model model
    ) {
        model.addAttribute("title", title);
        model.addAttribute("content", content);
        model.addAttribute("tag", tag);
        //标签组
        TagCache tagCache = new TagCache();
        List<TagDto> tags = tagCache.gettags();
        model.addAttribute("tags", tags);
        //防止输入的问题为空
        if (title == null || title.equals("")) {
            model.addAttribute("error", "The title cannot be empty");
            return "publish";
        }
        if (content == null || content.equals("")) {
            model.addAttribute("error", "Content cannot be empty");
            return "publish";
        }
        //获取当前登陆用户的信息
        User user = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                user = userMapper.findBytoken(token); //待定
            }
        }
        //将问题上传到数据库
        Blog post = new Blog();
        post.setTitle(title);
        post.setContent(content);
        post.setTag(tag);
        post.setAuthor_id(user.getId()); //待定
        post.set_post_time(System.currentTimeMillis());
        post.set_edit_time(System.currentTimeMillis());
        if (id == -1) {
            postMapper.createquestion(post);
        } else {
            post.setId(id);
            postMapper.updatequestion(post);
        }
        return "redirect:/index";
    }

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id")int id,
                       Model model){
        Blog post= postMapper.getbyId(id);
        model.addAttribute("title", post.getTitle());
        model.addAttribute("description", post.getContent());
        model.addAttribute("tag", post.getTag());
        //用来标识问题是修改而不是重新创建
        model.addAttribute("id",post.getId());
        return "publish";
    }
}

