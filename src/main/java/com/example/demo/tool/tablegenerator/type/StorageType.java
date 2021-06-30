package com.example.demo.tool.tablegenerator.type;

public enum StorageType {
    PRIMARY_KEY("PRIMARY KEY"),
    NOT_NULL("NOT NULL"),
    UNIQUE("UNIQUE"),
    UNSIGNED("UNSIGNED"),
    AUTO_INCREMENT("AUTO_INCREMENT");

    private final String storageTypeName;

    StorageType(String storageTypeName) {
        this.storageTypeName = storageTypeName;
    }

    public String getStorageTypeName() {
        return storageTypeName;
    }
}