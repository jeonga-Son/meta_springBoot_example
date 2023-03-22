package com.example.restservice.user;

import com.example.restservice.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

//    private static List<User> users = new ArrayList<>();

//    private static int userCount = 3;

    // user 객체 생성
//    static {
//        users.add(new User(1, "kosa", new Date(), "pass1", "990909-111111"));
//        users.add(new User(2, "meta", new Date(), "pass2", "890909-111111"));
//        users.add(new User(3, "naver", new Date(), "pass3", "790909-111111"));
//    }

    public List<User> findAll() {
        return userMapper.findAllUsers();
    }

    public User save(User user) {
        userMapper.createUser(user);

        return user;
    }

    public User findOne(int id) {
        return userMapper.findUser(id);
    }

    public User deleteById(int id) {
        User user = userMapper.findUser(id);

        userMapper.deleteUser(id);

        return user;
    }

    // Integer <-> int 와 boxing과 unboxing이 자유롭게 이루어진다.
    // Double과 Long도 같은 맥락이다.
//    public User findOne(int id) {
//        for(User user : users) {
//            if (user.getId() == id) {
//                return user;
//            }
//        }
//        return null;
//    }
//
//    public User save(User user) {
//        if(user.getId() == null) {
//            user.setId(++userCount);
//        }
//        users.add(user);
//
//        return user;
//    }
//
//    public User deleteById(int id) {
//        Iterator<User> iterator = users.iterator();
//
//        while(iterator.hasNext()) {
//            User user = iterator.next();
//
//            if(user.getId() == id) {
//                iterator.remove();
//                return user;
//            }
//        }
//        return null;
//    }
}
