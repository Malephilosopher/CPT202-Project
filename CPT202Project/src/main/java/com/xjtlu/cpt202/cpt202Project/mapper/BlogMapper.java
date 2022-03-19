package com.xjtlu.cpt202.cpt202Project.mapper;

import com.xjtlu.cpt202.cpt202Project.entity.Blog;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BlogMapper {
    @Insert("insert into question(title,description,createid,tag,createtime) values (#{title},#{description},#{createid},#{tag},#{createtime})")
    void createquestion(Blog Blog);

    @Select("select * from question order by createtime desc limit #{offset},#{size} ")
    List<Blog> list(@Param("offset") int offset, @Param("size") int size);

    @Select("select count(1) from question")
    int count();

    @Select("select * from question where createid=#{userid} limit #{offset},#{size}")
    List<Blog> listbyid(@Param("userid") int userid, @Param("offset") int offset, @Param("size") int size);

    @Select("select count(1) from question where createid=#{userid}")
    int countbyid(int userid);

    @Select("select * from question where id=#{id}")
    Blog getbyId(int id);

    @Update("update question set title=#{title},description=#{description},tag=#{tag},createtime=#{createtime} where id=#{id}")
    void updatequestion(Blog Blog);

    @Update("update question set view_count=view_count+1 where id=#{id}")
    void updateView(int id);

    @Update("update question set comment_count=comment_count+1 where id=#{parent_id}")
    void updatecomment(int parent_id);

    @Select("select * from question where tag REGEXP #{result} and id!=#{id} limit 0,10")
    List<Blog> getbytag(@Param("id") int id, @Param("result") String result);

    @Select("select title from question where id=#{outerid}")
    String gettitlebyid(int outerid);

    @Select("select * from question order by view_count desc limit 0,10")
    List<Blog> gettopten();
}

