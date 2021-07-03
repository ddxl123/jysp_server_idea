package com.example.demo.tool.tablegenerator.annotation;

import com.example.demo.tool.tablegenerator.type.DataType;
import com.example.demo.tool.tablegenerator.type.StorageType;

import java.lang.annotation.*;

/**
 * @author 10338
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface OutColumnTimestamp {

    DataType dataType() default DataType.DATETIME;

    StorageType[] storageTypes() default {};
}
