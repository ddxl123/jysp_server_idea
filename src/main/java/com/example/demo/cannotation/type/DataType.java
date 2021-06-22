package com.example.demo.cannotation.type;

public enum DataType {

    TINYINT("TINYINT", -1),
    INT("INT", -1),
    BIGINT("BIGINT", -1),

    CHAR_20("CHAR", 25),
    CHAR_250("CHAR", 255),
    VARCHAR_500("VARCHAR", 550),
    VARCHAR_1000("VARCHAR", 1050),
    VARCHAR_5000("VARCHAR", 5050),
    VARCHAR_10000("VARCHAR", 10050),

    TIMESTAMP("TIMESTAMP", -1);


    private String typeName;
    private int fixLength;

    DataType(String typeName, int fixLength) {
        this.typeName = typeName;
        this.fixLength = fixLength;
    }


    public String getTypeName() {
        return typeName;
    }

    public int getFixLength() {
        return getFixLength();
    }
}
