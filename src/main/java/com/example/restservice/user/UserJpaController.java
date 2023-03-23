package com.example.restservice.user;

import com.example.restservice.post.PostService;
import com.example.restservice.post.entity.Post;
import com.example.restservice.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/jpa/users")
public class UserJpaController {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    /**
     * 모든 유저 조회 API
     * [GET] /jpa/users
     * @return List<User>
     * */
    @GetMapping("")  // (GET) localhost:8080/jpa/users
    public List<User> retrieveAllUsers() {
        return userService.findAll();
    }

    /**
     * 유저 조회 API
     * [GET] /jpa/users/:id
     * @return EntityModel<User>
     * */
    @GetMapping("{id}") // (GET) localhost:8080/jpa/users/:id
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        User user = userService.findOne(id);

        if(user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        return EntityModel.of(user,
                linkTo(methodOn(UserJpaController.class).retrieveAllUsers()).withRel("all-users"));

//        // optional 객체일 때 ver
//        Optional<User> user = userRepository.findById(id);

//        // optional 객체일 때 ver isPresent() 사용
//        if(!user.isPresent()) {
//            throw new UserNotFoundException(String.format("ID[%s] not found", id));
//        }

        // optional 객체일 때 ver
//        return EntityModel.of(user.get(),
//                linkTo(methodOn(UserJpaController.class).retrieveAllUsers()).withRel("all-users"));
    }

    /**
     * 유저 생성 API
     * [POST] /jpa/users
     * @return ResponseEntity<User>
     * */
    @PostMapping("") // (POSt) localhost:8080/jpa/users
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
//
//    /**
//     * 유저 삭제 API
//     * [DELETE] /jpa/users/:id
//     * */
//    @DeleteMapping("/{id}") // (DELETE) localhost:8080/jpa/users/:id
//    public void deleteUser(@PathVariable int id) {
//        userRepository.deleteById(id);
//    }

    /**
     * 유저 게시글 조회 API
     * [GET] /jpa/users/:id/posts
     * @return List<Post>
     * */
    @GetMapping("/{id}/posts")  // (GET) localhost:8080/jpa/users/:id/posts
    public List<Post> retrieveAllPostsForUser(@PathVariable int id) { //@PathVariable param값이 하나면 그냥 int id 바로 써도 된다.
        User user = userService.findOne(id);

        if(user.equals(null)) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        List<Post> posts = postService.findAllPosts(id);

        return posts;
    }

    /**
     * 유저 게시글 생성 API
     * [POST] /jpa/users/:id/posts
     * @return User
     * */
    @PostMapping("/{id}/posts")  // (POST) localhost:8080/jpa/users/:id/posts
    public ResponseEntity<Post> createPostsForUser(@PathVariable int id, @RequestBody Post post) {
        User user = userService.findOne(id);
        System.out.println("user!!!!!!!!!!!!: " + user);

        if(user.equals(null)) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

//        post.setUser(user.get());
//        Post savedPost = postRepository.save(post);

        Post getPost = postService.save(post, id);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(getPost.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
//
//    /**
//     * 유저 게시글 상세 조회 API
//     * [GET] /users/:id/posts/:post_id
//     * @return User
//     * */
////    @GetMapping("{id}/posts/{post_id}")  // (GET) localhost:8080/jpa/users/:id/posts/:post_id"
////    public Post retrieveDetailsUser(@PathVariable("id") int id, @PathVariable("post_id") int post_id) {
////
////    }
}
