package com.example.restservice.post;

import com.example.restservice.post.entity.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    public List<Post> findAllPosts(int userId);

    public void createPost(Post post);

}
