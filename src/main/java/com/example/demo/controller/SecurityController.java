package com.example.demo.controller;

import com.example.demo.mapper.EmailVerifyMapper;
import com.example.demo.uniteurl.PrefixURL;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityController {

    public static final String home = PrefixURL.login_required + "/home";
    public static final String login_page = PrefixURL.no_login_required + "/login_page";
    public static final String authentication_success = PrefixURL.no_login_required + "/success";
    public static final String authentication_failure = PrefixURL.no_login_required + "/failure";
    public static final String on_login = PrefixURL.no_login_required + "/on_login";

    @NonNull
    private final EmailVerifyMapper emailVerifyMapper;

    @GetMapping(home)
    public String aa() {
        return "fuc home";
    }

    @GetMapping(login_page)
    public String login_page() {
        return "fuc login_page";
    }

    @PostMapping(authentication_success)
    public String success() {
        return "fuc success";
    }

    @PostMapping(authentication_failure)
    public String fail(@RequestHeader HttpHeaders headers, @RequestParam Map<String, String> param, @RequestBody String body) {
        System.out.println("headers");
        System.out.println(param);
        System.out.println(body);
        return "fuc fail";
    }

//    @GetMapping("/vip")
//    public String vip() {
//        return "fuc vip";
//    }
}
