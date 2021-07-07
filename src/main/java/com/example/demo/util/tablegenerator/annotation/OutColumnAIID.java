package com.example.demo.util.tablegenerator.annotation;

import com.example.demo.util.tablegenerator.type.DataType;
import com.example.demo.util.tablegenerator.type.StorageType;

import java.lang.annotation.*;

/**
 * @author 10338
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface OutColumnAIID {

    DataType dataType() default DataType.BIGINT;

    StorageType[] storageTypes() default {
            StorageType.UNSIGNED,
    };
}
