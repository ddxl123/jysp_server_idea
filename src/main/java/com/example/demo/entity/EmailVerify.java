package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.tool.TableGenerator.Annotations.MappingColumn;
import com.example.demo.tool.TableGenerator.Annotations.MappingColumnPYID;
import com.example.demo.tool.TableGenerator.Annotations.MappingColumnTimestamp;
import com.example.demo.tool.TableGenerator.Annotations.MappingTable;
import com.example.demo.tool.TableGenerator.type.DataType;
import lombok.Data;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @author 10338
 */
@Data
@MappingTable
@TableName
public class EmailVerify {
    @MappingColumnPYID
    @TableId(type = IdType.AUTO)
    private BigInteger id;

    @MappingColumn(dataType = DataType.CHAR_50)
    @TableField
    private String email;

    @MappingColumn(dataType = DataType.INT)
    @TableField
    private String code;

    @MappingColumnTimestamp
    @TableField
    private Timestamp createdAt;

    @MappingColumnTimestamp
    @TableField
    private Timestamp updatedAt;
}
