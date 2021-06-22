package com.example.demo.TableGenerator.type;

public enum DataType {

    NONE(""),

    TINYINT("TINYINT"),
    INT("INT"),
    BIGINT("BIGINT"),

    CHAR_20("CHAR(20)"),
    CHAR_250("CHAR(250)"),
    VARCHAR_500("VARCHAR(500)"),
    VARCHAR_1000("VARCHAR(1000)"),
    VARCHAR_5000("VARCHAR(5000)"),
    VARCHAR_10000("VARCHAR(10000)"),

    TIMESTAMP("TIMESTAMP");

    private String typeName;

    DataType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }


}
