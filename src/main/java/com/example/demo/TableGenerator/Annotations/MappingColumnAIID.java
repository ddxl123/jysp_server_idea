package com.example.demo.TableGenerator.Annotations;

import com.example.demo.TableGenerator.type.DataType;
import com.example.demo.TableGenerator.type.StorageType;

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
