package com.growthhub.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/users")
public class TestController {
    @GetMapping
    public String test(HttpServletRequest request) {
        return request.getHeader("User-Id");
    }

    @GetMapping("/test")
    public String test2() {
        return "test";
    }
}