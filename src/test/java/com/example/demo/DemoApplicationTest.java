package com.example.demo;


import com.example.demo.config.passwordconfig.Passworder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTest {

    @Autowired
    Passworder passworder;

    @Test
    public void a() {
        System.out.println(passworder.generateRandomPassword(16, true));
        System.out.println(passworder.generateRandomPassword(16, true));
        System.out.println(passworder.generateRandomPassword(16, true));
        System.out.println(passworder.generateRandomPassword(16, true));
        System.out.println(passworder.generateRandomPassword(16, true));
        System.out.println(passworder.generateRandomPassword(16, true));
        System.out.println(passworder.generateRandomPassword(16, true));
        System.out.println(passworder.generateRandomPassword(16, true));
        System.out.println(passworder.generateRandomPassword(16, true));
        System.out.println(passworder.generateRandomPassword(16, true));
        System.out.println(passworder.generateRandomPassword(16, true));
    }


}
