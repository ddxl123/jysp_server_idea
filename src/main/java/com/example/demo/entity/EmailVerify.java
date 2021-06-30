package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class EmailVerify {
    @OutColumnPYID
    @TableId(type = IdType.AUTO)
    private BigInteger id;

    @OutColumn(dataType = DataType.CHAR_50)
    @TableField
    private String email;

    @OutColumn(dataType = DataType.INT)
    @TableField
    private String code;

    @OutColumnTimestamp
    @TableField
    private Timestamp createdAt;

    @OutColumnTimestamp
    @TableField
    private Timestamp updatedAt;
}
