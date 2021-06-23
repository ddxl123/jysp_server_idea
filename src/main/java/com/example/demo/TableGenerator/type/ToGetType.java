package com.example.demo.TableGenerator.type;

import lombok.Data;

@Data
public class ToGetType {
    private final DataType dataType;
    private final StorageType[] storageTypes;

    public ToGetType(DataType dataType, StorageType[] storageTypes) {
        this.dataType = dataType;
        this.storageTypes = storageTypes;
    }
}
