package com.example.demo.TableGenerator.Annotations;

import com.example.demo.TableGenerator.type.DataType;
import com.example.demo.TableGenerator.type.StorageType;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface MappingColumnTimestamp {

    DataType dataType() default DataType.TIMESTAMP;

    StorageType[] storageTypes() default {};
}
