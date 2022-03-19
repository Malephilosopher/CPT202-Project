package com.Zeng.CPT202.controller;

import com.Zeng.CPT202.Cache.TagCache;
import com.Zeng.CPT202.dto.TagDto;
import com.Zeng.CPT202.entity.Post;
import com.Zeng.CPT202.entity.User;
import com.Zeng.CPT202.mapper.PostMapper;
import com.Zeng.CPT202.mapper.UserMapper;
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
public class publishController {

    @Resource
    private UserMapper userMapper;
    @Resource
    private PostMapper postMapper;

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
        if (title == null || title == "") {
            model.addAttribute("error", "The title cannot be empty");
            return "publish";
        }
        if (content == null || content == "") {
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
        Post post = new Post();
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
        Post post= postMapper.getbyId(id);
        model.addAttribute("title", post.getTitle());
        model.addAttribute("description", post.getContent());
        model.addAttribute("tag", post.getTag());
        //用来标识问题是修改而不是重新创建
        model.addAttribute("id",post.getId());
        return "publish";
    }
}

