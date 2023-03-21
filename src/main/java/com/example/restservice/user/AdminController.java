package com.example.restservice.user;

import com.example.restservice.user.model.User;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private UserService service;

    public AdminController(UserService service) {
        this.service = service;
    }

    /**
     * 모든 유저 조회 API
     * [GET] /users
     * @return List<User>
     * */
    @GetMapping("") // (GET) localhost:8080/users
    public MappingJacksonValue retrieveAllUsers() {
        List<User> users = service.findAll();

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDate", "ssn", "password"); // 출력하고 싶은 것들을 필터링을 적용한다.

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter); // @JsonFilter("UserInfo")

        MappingJacksonValue mapping = new MappingJacksonValue(users); // 매핑시켜준다.
        mapping.setFilters(filters);

        return mapping;
    }

    /**
     * 유저 생성 API
     * [POST] /users
     * @return ResponseEntity
     * */
    @PostMapping("") // (POST) localhost:8080/users
    // json을 User 객체로 변환해줄 수 있는 것 이 필요하기 때문에 @RequestBody를 써야한다.
    public ResponseEntity createUser(@Valid @RequestBody User user) { // @Valid 유효성 검사 이루어진다.
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()) // 이 조회한 id가 path의 id로 들어간다.
                .toUri();

        return ResponseEntity.created(location).build();
    }

    /**
     * 유저 조회 API
     * [GET] /users/:id
     * @return User
     * */
    @GetMapping("/{id}")  // (GET) localhost:8080/users/:id
    public MappingJacksonValue retrieveOneUser(@PathVariable int id) { // MappingJacksonValue 안에 필터링된 User 객체가 들어가있다.
        User user = service.findOne(id);

        if(user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDate", "ssn"); // 출력하고 싶은 것들을 필터링을 적용한다.

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter); // @JsonFilter("UserInfo")

        MappingJacksonValue mapping = new MappingJacksonValue(user); // 매핑시켜준다.
        mapping.setFilters(filters);

        return mapping;
    }

    /**
     * 유저 삭제 API
     * [GET] /users/:id
     * */
    @DeleteMapping("/{id}")  // (DELETE) localhost:8080/users/:id
    public void deleteUser(@PathVariable int id) {
        User user = service.deleteById(id);

        if(user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
    }
}
