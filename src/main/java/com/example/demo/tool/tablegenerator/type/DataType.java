package com.example.demo.tool.tablegenerator.type;

import java.math.BigInteger;

/**
 * @author 10338
 */
public enum DataType {

    TINYINT("TINYINT", Integer.class),
    INT_INTEGER("INT", Integer.class),
    INT_LONG("INT", Long.class),
    BIGINT("BIGINT", BigInteger.class),

    CHAR_20("CHAR(25)", String.class),
    CHAR_50("CHAR(55)", String.class),
    CHAR_100("CHAR(105)", String.class),
    CHAR_250("CHAR(255)", String.class),
    VARCHAR_500("VARCHAR(550)", String.class),
    VARCHAR_1000("VARCHAR(1050)", String.class),
    VARCHAR_5000("VARCHAR(5050)", String.class),
    VARCHAR_10000("VARCHAR(10050)", String.class);

    private final String databaseName;
    private Class<?> javaClass;

    DataType(String typeName, Class<?> javaClass) {
        this.databaseName = typeName;
        this.javaClass = javaClass;
    }

    public Class<?> getJavaClass() {
        return javaClass;
    }

    public String getDatabaseName() {
        return databaseName;
    }


}
