package com.example.restservice.user;

import com.example.restservice.user.model.User;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.swing.text.html.Option;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/jpa/users")
public class UserJpaController {
    @Autowired
    private UserRepository userRepository;

    /**
     * 모든 유저 조회 API
     * [GET] /jpa/users
     * @return List<User>
     * */
    @GetMapping("")  // (GET) localhost:8080/jpa/users
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    /**
     * 모든 유저 조회 API
     * [GET] /jpa/users/:id
     * @return EntityModel<User>
     * */
    @GetMapping("{id}") // (GET) localhost:8080/jpa/users/:id
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        User user = userRepository.findById(id).orElse(null);

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
        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    /**
     * 유저 삭제 API
     * [DELETE] /jpa/users/:id
     * */
    @DeleteMapping("/{id}") // (DELETE) localhost:8080/jpa/users/:id
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }
}