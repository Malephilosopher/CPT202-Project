package com.xjtlu.cpt202.cpt202Project;

import com.xjtlu.cpt202.cpt202Project.controller.CommentController;
import com.xjtlu.cpt202.cpt202Project.entity.Comment;
import com.xjtlu.cpt202.cpt202Project.service.Impl.CommentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {CPT202ProjectApplication.class})    // classes 项目的主启动类
class ModaoApplicationTests {

    @Autowired
    CommentServiceImpl CSI;

    @Test
    public void testListComments() {
        List<Comment> comments = CSI.listCommentByBlogId(1);
        System.out.println(comments);

    }
}