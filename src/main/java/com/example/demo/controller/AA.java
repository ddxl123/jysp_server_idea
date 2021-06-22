package com.example.demo.controller;

import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AA {
    @GetMapping("/")
    public String aa(){
        return "123";
    }

    @GetMapping("/bb")
    public String bb(){
        return "456";
    }


    @GetMapping("/cc")
    public void contextLoads() {
    }
}
