package com.example.demo.TableGenerator.Annotations;

import com.example.demo.TableGenerator.type.DataType;
import com.example.demo.TableGenerator.type.StorageType;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface MappingColumn {
    DataType dataType() default DataType.NONE;

    StorageType[] storageTypes() default {};
}

