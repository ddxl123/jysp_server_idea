package com.example.demo.cannotation;

import com.example.demo.cannotation.type.DataType;
import com.example.demo.cannotation.type.StorageType;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface MapField {
    DataType dataType();

    StorageType[] storageTypes();
}
