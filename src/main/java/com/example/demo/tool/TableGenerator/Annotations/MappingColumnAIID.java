package com.example.demo.tool.TableGenerator.Annotations;

import com.example.demo.tool.TableGenerator.type.DataType;
import com.example.demo.tool.TableGenerator.type.StorageType;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface MappingColumnAIID {

    DataType dataType() default DataType.BIGINT;

    StorageType[] storageTypes() default {
            StorageType.UNSIGNED,
    };
}