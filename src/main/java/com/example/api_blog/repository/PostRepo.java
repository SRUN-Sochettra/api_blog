package com.example.api_blog.repository;

import com.example.api_blog.model.entity.Post;
import org.apache.ibatis.annotations.*;

@Mapper
public interface PostRepo {
    // Fix this in PostRepo.java:
    @Insert("insert into posts(title,description,user_id,created_at) values (#{title},#{description},#{userId}, now())")

    @Results(id = "PostMapper",value = {
            @Result(property = "title",column = "title"),
            @Result(property = "description",column = "description"),
            @Result(property = "userId",column = "user_id")

    })
    @Options(useGeneratedKeys = true, keyProperty = "postId", keyColumn = "post_id")
    void addPost(Post post);
}