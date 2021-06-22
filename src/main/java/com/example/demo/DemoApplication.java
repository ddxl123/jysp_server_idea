package com.example.demo;

import com.example.demo.cannotation.GenerateTableScanner;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.demo.mapper")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}

 class GenerateTableRun {
    public static void main(String[] args) {
      GenerateTableScanner generateTableScanner = new GenerateTableScanner();
      generateTableScanner.setPackageName("com.example.demo.entity");
      generateTableScanner.generate();
    }

}
