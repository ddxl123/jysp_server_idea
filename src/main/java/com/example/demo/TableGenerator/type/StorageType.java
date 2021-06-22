package com.example.demo.TableGenerator.type;

public enum StorageType {
    NONE(""),
    PRIMARY_KEY("PRIMARY KEY"),
    NOT_NULL("NOT NULL"),
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