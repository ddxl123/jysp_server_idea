package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.TableGenerator.Annotations.MappingColumn;
import com.example.demo.TableGenerator.Annotations.MappingColumnPYID;
import com.example.demo.TableGenerator.Annotations.MappingColumnTimestamp;
import com.example.demo.TableGenerator.Annotations.MappingTable;
import com.example.demo.TableGenerator.type.DataType;
import lombok.Data;

import java.math.BigInteger;
import java.sql.Timestamp;

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
