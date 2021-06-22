package com.example.demo.cannotation.type;

public enum StorageType {
    NOT_NULL("NOT NULL"),
    PRIMARY_KEY("PRIMARY KEY"),
    UNIQUE("UNIQUE"),
    UNSIGNED("UNSIGNED"),
    AUTO_INCREMENT("AUTO_INCREMENT");

    private String storageTypeName;

    StorageType(String storageTypeName) {
        this.storageTypeName = storageTypeName;
    }

    public String getStorageTypeName() {
        return storageTypeName;
    }
}