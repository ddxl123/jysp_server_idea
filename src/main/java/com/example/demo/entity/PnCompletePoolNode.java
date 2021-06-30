package com.example.demo.entity;

import com.example.demo.tool.tablegenerator.annotation.*;
import com.example.demo.tool.tablegenerator.type.DataType;
import lombok.Data;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @author 10338
 */
@Data
@OutTable
public class PnCompletePoolNode {
    @OutColumnPYID
    private BigInteger id;

    @OutColumnAIID
    private BigInteger userAiid;

    @OutColumnAIID
    private BigInteger usedRawRuleAiid;

    @OutColumn(dataType = DataType.TINYINT)
    private Integer type;

    @OutColumn(dataType = DataType.CHAR_20)
    private String name;

    @OutColumn(dataType = DataType.CHAR_50)
    private String position;

    @OutColumnTimestamp
    private Timestamp createdAt;

    @OutColumnTimestamp
    private Timestamp updatedAt;
}
