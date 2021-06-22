package com.example.demo.entity;

import com.example.demo.cannotation.type.DataType;
import com.example.demo.cannotation.MapField;
import com.example.demo.cannotation.type.StorageType;
import lombok.Data;

import java.math.BigInteger;

@Data
public class User {

    @MapField(dataType = DataType.BIGINT, storageTypes = {StorageType.AUTO_INCREMENT, StorageType.UNSIGNED})
    private BigInteger id;
    private String username;
    private String password;
    private byte age;
}

