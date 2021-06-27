package com.example.demo.tool.TableGenerator.type;

public enum DataType {

    TINYINT("TINYINT"),
    INT("INT"),
    BIGINT("BIGINT"),

    CHAR_20("CHAR(25)"),
    CHAR_50("CHAR(55)"),
    CHAR_100("CHAR(105)"),
    CHAR_250("CHAR(255)"),
    VARCHAR_500("VARCHAR(550)"),
    VARCHAR_1000("VARCHAR(1050)"),
    VARCHAR_5000("VARCHAR(5050)"),
    VARCHAR_10000("VARCHAR(10050)"),

    TIMESTAMP("TIMESTAMP");

    private final String typeName;

    DataType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }


}
