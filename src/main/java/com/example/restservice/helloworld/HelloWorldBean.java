package com.example.restservice.helloworld;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor // 생성자 만들어주기 때문에 따로 만들 필요 없음.
@NoArgsConstructor // default 생성자 만들기
public class HelloWorldBean {
    private String message;

}
