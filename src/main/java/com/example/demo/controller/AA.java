package com.example.demo.controller;

import com.example.demo.mapper.EmailVerifyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AA {

    @NonNull
    private final EmailVerifyMapper emailVerifyMapper;

    @GetMapping("/home")
    public String aa() {
        return "fuc home";
    }

    @GetMapping("/login_page")
    public String login_page() {
        return "fuc login_page";
    }

    @PostMapping("/success")
    public String success() {
        return "fuc success";
    }

    @PostMapping("/fail")
    public String fail() {
        return "fuc fail";
    }

    @GetMapping("/vip")
    public String vip() {
        return "fuc vip";
    }
}
