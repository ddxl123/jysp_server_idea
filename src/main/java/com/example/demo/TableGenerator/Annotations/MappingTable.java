package com.example.demo.TableGenerator.Annotations;


import java.lang.annotation.*;


@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface MappingTable {
}

