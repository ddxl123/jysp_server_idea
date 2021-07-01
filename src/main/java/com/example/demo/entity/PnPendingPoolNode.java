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
public class PnPendingPoolNode {
    @OutColumnPYID
    private BigInteger id;

    @OutColumnAIID
    private BigInteger userAiid;

    @OutColumnAIID
    private BigInteger recommendRawRuleAiid;

    @OutColumn(dataType = DataType.TINYINT)
    private Integer type;

    @OutColumn(dataType = DataType.CHAR_20)
    private String name;

    @OutColumn(dataType = DataType.CHAR_50)
    private String position;

    @OutColumnTimestamp
    private Long createdAt;

    @OutColumnTimestamp
    private Long updatedAt;
}
