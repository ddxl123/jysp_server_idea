package com.example.demo.entity;

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
