package com.example.demo.entity;

import com.example.demo.TableGenerator.Annotations.*;
import com.example.demo.TableGenerator.type.DataType;
import lombok.Data;

import java.math.BigInteger;
import java.sql.Timestamp;

@Data
@MappingTable
public class PnCompletePoolNode {
    @MappingColumnPYID
    private BigInteger id;

    @MappingColumnAIID
    private BigInteger userAiid;

    @MappingColumnAIID
    private BigInteger usedRawRuleAiid;

    @MappingColumn(dataType = DataType.TINYINT)
    private Integer type;

    @MappingColumn(dataType = DataType.CHAR_20)
    private String name;

    @MappingColumn(dataType = DataType.CHAR_50)
    private String position;

    @MappingColumnTimestamp
    private Timestamp createdAt;

    @MappingColumnTimestamp
    private Timestamp updatedAt;
}
