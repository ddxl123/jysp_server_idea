package com.example.demo.tool.TableGenerator.Annotations;

import com.example.demo.tool.TableGenerator.type.DataType;
import com.example.demo.tool.TableGenerator.type.StorageType;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface MappingColumn {
    DataType dataType();

    StorageType[] storageTypes() default {};
}

