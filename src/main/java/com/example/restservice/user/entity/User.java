package com.example.restservice.user.entity;

import com.example.restservice.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties(value = {"password", "ssn"})
//@JsonFilter("UserInfo") // controller에 따라서 권한이 다르기 때문에 각각 설정해주기 위한 작업
//@Entity // 해당 클래스가 JPA의 엔티티임을 나타낸다. 주키가 꼭 있어야 한다.
public class User {
//    @Id // 기본키임을 나타낸다.
//    @GeneratedValue // 시퀀스 생성 및 기본키 생성 전략을 나타낸다.
    private Integer id;
    @Size(min = 2, message = "Name은 2글자 이상 입력하세요.")
    private String name;
    private Date joinDate;

    //@JsonIgnore // 데이터 값은 담겼지만 노출은 제한한다.
    private String password;
    private String ssn; // 주민등록번호

//    @OneToMany(mappedBy = "user") // user 입장에서는 one이다.
    private List<Post> posts; // post는 여러개이다.

//    public User(Integer id, String name, Date joinDate, String password, String ssn) {
//        this.id = id;
//        this.name = name;
//        this.joinDate = joinDate;
//        this.password = password;
//        this.ssn = ssn;
//    }
}
