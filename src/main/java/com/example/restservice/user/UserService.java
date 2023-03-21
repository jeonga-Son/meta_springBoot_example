package com.example.restservice.user;

import com.example.restservice.user.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    private static List<User> users = new ArrayList<>();

    private static int userCount = 3;

    static {
        users.add(new User(1, "kosa", new Date()));
        users.add(new User(2, "meta", new Date()));
        users.add(new User(3, "naver", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    // Integer <-> int 와 boxing과 unboxing이 자유롭게 이루어진다.
    // Double과 Long도 같은 맥락이다.
    public User findOne(int id) {
        for(User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User save(User user) {
        if(user.getId() == null) {
            user.setId(++userCount);
        }
        users.add(user);

        return user;
    }
}
