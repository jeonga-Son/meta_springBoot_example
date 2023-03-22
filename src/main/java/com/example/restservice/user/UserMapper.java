package com.example.restservice.user;

import com.example.restservice.user.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    public List<User> findAllUsers();

    public User findUser(int id);

    public void createUser(User user);

    public void deleteUser(int id);
}
