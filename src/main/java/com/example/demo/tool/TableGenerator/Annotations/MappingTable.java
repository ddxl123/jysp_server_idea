package com.example.demo.tool.TableGenerator.Annotations;


import java.lang.annotation.*;


@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface MappingTable {
}

