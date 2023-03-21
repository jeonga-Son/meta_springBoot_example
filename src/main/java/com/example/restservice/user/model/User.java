package com.example.restservice.user.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties(value = {"password", "ssn"})
//@JsonFilter("UserInfo") // controller에 따라서 권한이 다르기 때문에 각각 설정해주기 위한 작업
public class User {
    private Integer id;
    @Size(min = 2, message = "Name은 2글자 이상 입력하세요.")
    private String name;
    private Date joinDate;
//    @JsonIgnore // 데이터 값은 담겼지만 노출은 제한한다.
    private String password;
    private String ssn; // 주민등록번호
}
