package com.example.demo.entity;

import com.example.demo.TableGenerator.Annotations.MappingColumn;
import com.example.demo.TableGenerator.Annotations.MappingColumnPYID;
import com.example.demo.TableGenerator.Annotations.MappingTable;
import com.example.demo.TableGenerator.type.DataType;
import lombok.Data;

import java.math.BigInteger;

@Data
@MappingTable
public class TestUser {

    @MappingColumnPYID
    private BigInteger id;

    @MappingColumn(dataType = DataType.CHAR_20)
    private String username;

    @MappingColumn(dataType = DataType.CHAR_20)
    private String password;

    @MappingColumn(dataType = DataType.TINYINT)
    private Integer age;

    @MappingColumn(dataType = DataType.VARCHAR_500)
    private String userInfo;
}

