package com.example.demo.controller.controller.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 10338
 */
@Data
@RestController
@AllArgsConstructor
@RequestMapping(
        path = "/test"
)
public class TestController {
    @GetMapping("/get")
    public String getTest() {
        return "test get method";
    }

    @PostMapping("/post")
    public String postTest() {
        return "test post method";
    }
}
