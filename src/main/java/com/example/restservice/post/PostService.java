package com.example.restservice.post;

import com.example.restservice.post.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostMapper postMapper;

    public List<Post> findAllPosts(int userId) {
        System.out.println("userId!!!!! : " + userId);
        return postMapper.findAllPosts(userId);
    }

    public Post save(Post post, int userId) {
        System.out.println("2. Post!!!!!!!!!!!!! : " + post);
        post.setUser_id(userId);
        postMapper.createPost(post);

        return post;
    }
}
