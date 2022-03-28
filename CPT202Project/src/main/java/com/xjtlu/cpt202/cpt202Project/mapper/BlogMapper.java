package com.xjtlu.cpt202.cpt202Project.mapper;

import com.xjtlu.cpt202.cpt202Project.entity.Blog;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BlogMapper {
    @Insert("insert into blog(title,description,createid,tag,createtime) values (#{title},#{description},#{createid},#{tag},#{createtime})")
    void createblog(Blog Blog);

    @Select("select * from blog order by createtime desc limit #{offset},#{size} ")
    List<Blog> list(@Param("offset") int offset, @Param("size") int size);

    @Select("select count(1) from blog")
    int count();

    @Select("select * from blog where createid=#{userid} limit #{offset},#{size}")
    List<Blog> listbyid(@Param("userid") int userid, @Param("offset") int offset, @Param("size") int size);

    @Select("select count(1) from blog where createid=#{userid}")
    int countbyid(int userid);

    @Select("select * from blog where id=#{id}")
    Blog getbyId(int id);

    @Update("update blog set title=#{title},description=#{description},tag=#{tag},createtime=#{createtime} where id=#{id}")
    void updatequestion(Blog Blog);

    @Update("update blog set view_count=view_count+1 where id=#{id}")
    void updateView(int id);

    @Update("update blog set comment_count=comment_count+1 where id=#{parent_id}")
    void updatecomment(int parent_id);

    @Select("select * from blog where tag REGEXP #{result} and id!=#{id} limit 0,100")
    List<Blog> getbytag(@Param("id") int id, @Param("result") String result);

    @Select("select title from blog where id=#{outerid}")
    String gettitlebyid(int outerid);

    @Select("select * from blog order by view_count desc limit 0,100")
    List<Blog> gettopten();
}

