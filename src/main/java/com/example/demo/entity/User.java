package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.tool.tablegenerator.annotation.OutColumn;
import com.example.demo.tool.tablegenerator.annotation.OutColumnPYID;
import com.example.demo.tool.tablegenerator.annotation.OutColumnTimestamp;
import com.example.demo.tool.tablegenerator.annotation.OutTable;
import com.example.demo.tool.tablegenerator.type.DataType;
import lombok.Data;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @author 10338
 */
@Data
@OutTable
@TableName
public class User {

    @OutColumnPYID
    private BigInteger id;

    @OutColumn(dataType = DataType.CHAR_20)
    private String username;

    @OutColumn(dataType = DataType.CHAR_20)
    private String password;

    @OutColumn(dataType = DataType.CHAR_50)
    private String email;

    @OutColumnTimestamp
    private Long createdAt;

    @OutColumnTimestamp
    private Long updatedAt;


}
