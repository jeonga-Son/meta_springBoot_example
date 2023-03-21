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

//    @PostMapping("")
//    public String createUser() {
//        return "create User";
//    }

    /**
     * 유저 조회 API
     * [GET] /users/:id
     * @return User
     * */
    @GetMapping("/{id}")    // (GET) localhost:8080/users/:id
    public User retrieveOneUser(@PathVariable int id) {
        return service.findOne(id);
    }

//    @DeleteMapping("/{id}")
//    public String deleteUser() {
//        return "delete User";
//    }
//
//    @GetMapping("/{id}/posts")
//    public String retrieveAllPostsForUser() {
//        return "show User Posts";
//    }
//
//    @Post("/{id}/posts")
//    public String createPostsForUser() {
//        return "create User Posts";
//    }

//    @GetMapping("{id}/posts/{post_id}")
//    public String retrieveDetailsUser() {
//
//    }

}
