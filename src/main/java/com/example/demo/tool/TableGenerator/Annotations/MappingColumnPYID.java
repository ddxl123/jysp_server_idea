package com.example.demo.tool.TableGenerator.Annotations;

import com.example.demo.tool.TableGenerator.type.DataType;
import com.example.demo.tool.TableGenerator.type.StorageType;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface MappingColumnPYID {

    DataType dataType() default DataType.BIGINT;

    StorageType[] storageTypes() default {
            StorageType.UNSIGNED,
            StorageType.PRIMARY_KEY,
            StorageType.AUTO_INCREMENT,
            StorageType.NOT_NULL,
            StorageType.UNIQUE
    };
}
