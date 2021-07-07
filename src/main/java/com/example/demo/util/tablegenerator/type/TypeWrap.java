package com.example.demo.util.tablegenerator.type;

import lombok.Data;
import org.springframework.lang.NonNull;

/**
 * @author 10338
 */
@Data
public class TypeWrap {
    @NonNull
    private final DataType dataType;
    @NonNull
    private final StorageType[] storageTypes;

    public TypeWrap(@NonNull DataType dataType, @NonNull StorageType[] storageTypes) {
        this.dataType = dataType;
        this.storageTypes = storageTypes;
    }
}
