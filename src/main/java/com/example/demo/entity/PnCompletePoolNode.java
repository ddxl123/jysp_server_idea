package com.example.demo.entity;

import com.example.demo.tool.tablegenerator.annotation.*;
import com.example.demo.tool.tablegenerator.type.DataType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * @author 10338
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@OutTable
public class PnCompletePoolNode extends BaseEntity<PnCompletePoolNode>{
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
}
