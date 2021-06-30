package com.example.demo;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest()
class DemoApplicationTest {


    @Test
    public void a() {
        System.out.println(Math.random() * 9000 + 1000);
    }

}
