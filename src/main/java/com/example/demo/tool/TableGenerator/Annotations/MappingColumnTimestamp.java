package com.example.demo.tool.TableGenerator.Annotations;

import com.example.demo.tool.TableGenerator.type.DataType;
import com.example.demo.tool.TableGenerator.type.StorageType;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface MappingColumnTimestamp {

    DataType dataType() default DataType.TIMESTAMP;

    StorageType[] storageTypes() default {};
}
