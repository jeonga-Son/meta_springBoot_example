package com.example.restservice.user;

import com.example.restservice.user.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // @ResponseBody + @RequestController
@RequestMapping("/users")
public class UserController {
    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    /**
     * 모든 유저 조회 API
     * [GET] /users
     * @return List<User>
     * */
    @GetMapping("") // (GET) localhost:8080/users
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    /**
     * 유저 생성 API
     * [POST] /users
     * @return User
     * */
    @PostMapping("") // (POST) localhost:8080/users
    // json을 User 객체로 변환해줄 수 있는 것 이 필요하기 때문에 @RequestBody를 써야한다.
    public User createUser(@RequestBody User user) {
        return service.save(user);
    }

    /**
     * 유저 조회 API
     * [GET] /users/:id
     * @return User
     * */
    @GetMapping("/{id}")  // (GET) localhost:8080/users/:id
    public User retrieveOneUser(@PathVariable int id) {
        return service.findOne(id);
    }

    /**
     * 유저 삭제 API
     * [GET] /users/:id
     * @return User
     * */
//    @DeleteMapping("/{id}")  // (DELETE) localhost:8080/users/:id
//    public String deleteUser() {
//        return "delete User";
//    }

    /**
     * 유저 게시글 조회 API
     * [GET] /users/:id
     * @return User
     * */
//    @GetMapping("/{id}/posts")  // (GET) localhost:8080/users/:id/posts
//    public String retrieveAllPostsForUser() {
//        return "show User Posts";
//    }

    /**
     * 유저 게시글 생성 API
     * [POST] /users/:id/posts
     * @return User
     * */
//    @Post("/{id}/posts")  // (POST) localhost:8080/users/:id/posts
//    public String createPostsForUser() {
//        return "create User Posts";
//    }

    /**
     * 유저가 게시글 상세 조회 API
     * [GET] /users/:id/posts/:post_id
     * @return User
     * */
//    @GetMapping("{id}/posts/{post_id}")  // (GET) localhost:8080/users/:id/posts/:post_id"
//    public String retrieveDetailsUser() {
//
//    }
}
