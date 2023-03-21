package com.example.restservice.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloController {
    @Autowired
    private MessageSource messageSource; // Spring Container에서 생성되는 객체
    
    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World!!!";
    }

    @GetMapping("hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World Bean!!!");
    }

    @GetMapping("hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldBean2(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello WorldBean, %s", name));
    }

    @GetMapping("/hello-world-international")
    // Header 값을 "Accept-Language"라는 이름으로 전달받아야 한다.
    public String helloWorldInternational(@RequestHeader(name = "Accept-Language", required = false)Locale locale) {
        return messageSource.getMessage("greeting.message", null, locale);
    }
}
