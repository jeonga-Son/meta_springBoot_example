package com.example.restservice.user.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties(value = {"password", "ssn"})
//@JsonFilter("UserInfo") // controller에 따라서 권한이 다르기 때문에 각각 설정해주기 위한 작업
@Entity // 해당 클래스가 JPA의 엔티티임을 나타낸다. 주키가 꼭 있어야 한다.
public class User {
    @Id // 기본키임을 나타낸다.
    @GeneratedValue // 시퀀스 생성 및 기본키 생성 전략을 나타낸다.
    private Integer id;
    @Size(min = 2, message = "Name은 2글자 이상 입력하세요.")
    private String name;
    private Date joinDate;

    //@JsonIgnore // 데이터 값은 담겼지만 노출은 제한한다.
    private String password;
    private String ssn; // 주민등록번호
}
