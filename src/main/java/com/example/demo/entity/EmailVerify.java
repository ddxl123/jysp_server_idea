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
import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;

/**
 * @author 10338
 */
@OutTable
@Data
@Builder
@TableName
public class EmailVerify {
    @OutColumnPYID
    @TableId(type = IdType.AUTO)
    private BigInteger id;

    @OutColumn(dataType = DataType.CHAR_50)
    @TableField
    private String email;

    @OutColumn(dataType = DataType.INT_INTEGER)
    @TableField
    private Integer code;

    @OutColumnTimestamp
    @TableField
    private Long createdAt;

    @OutColumnTimestamp
    @TableField
    private Long updatedAt;
}
