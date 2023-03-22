package com.example.restservice.post.model;

import com.example.restservice.user.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
//    @Id
//    @GeneratedValue
    private Integer id;

    private String description;

    // Post 입장에서는 Many이기 때문에 ManyToOne이다.
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JsonIgnore // 데이터는 불러오지만 화면에 출력되지 않는다.
    private User user; // user가 필요할 때 user를 사용하도록 fetch = FetchType.LAZY
}
