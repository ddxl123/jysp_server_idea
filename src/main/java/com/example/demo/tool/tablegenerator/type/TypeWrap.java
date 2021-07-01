package com.example.demo.tool.tablegenerator.type;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author 10338
 */
@Data
public class TypeWrap {
    @NotNull
    private final DataType dataType;
    @NotNull
    private final StorageType[] storageTypes;

    public TypeWrap(DataType dataType, StorageType[] storageTypes) {
        this.dataType = dataType;
        this.storageTypes = storageTypes;
    }
}
