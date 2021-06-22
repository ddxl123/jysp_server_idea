package com.example.demo;


import com.example.demo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;

@SpringBootTest()
class DemoApplicationTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void aa(){
        BigInteger bigInteger = new BigInteger("12300000000000000000000000000000000000000000000000");
        System.out.println(bigInteger.toString());
    }

}
