package com.example.demo.entity;

import com.example.demo.tool.TableGenerator.Annotations.*;
import com.example.demo.tool.TableGenerator.type.DataType;
import lombok.Data;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @author 10338
 */
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
