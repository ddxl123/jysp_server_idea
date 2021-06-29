package com.example.demo.entity;

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
public class User {

    @MappingColumnPYID
    private BigInteger id;

    @MappingColumn(dataType = DataType.CHAR_20)
    private String username;

    @MappingColumn(dataType = DataType.CHAR_20)
    private String password;

    @MappingColumn(dataType = DataType.CHAR_50)
    private String email;

    @MappingColumnTimestamp
    private Timestamp createdAt;

    @MappingColumnTimestamp
    private Timestamp updatedAt;


}
