package com.example.demo.entity;

import com.example.demo.tool.tablegenerator.annotation.OutColumn;
import com.example.demo.tool.tablegenerator.annotation.OutColumnAIID;
import com.example.demo.tool.tablegenerator.annotation.OutTable;
import com.example.demo.tool.tablegenerator.type.DataType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigInteger;

/**
 * @author 10338
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@OutTable
public class PnMemoryPoolNode extends BaseEntity<PnMemoryPoolNode> {
    @OutColumnAIID
    private BigInteger userAiid;

    @OutColumnAIID
    private BigInteger usingRawRuleAiid;

    @OutColumn(dataType = DataType.TINYINT)
    private Integer type;

    @OutColumn(dataType = DataType.CHAR_20)
    private String name;

    @OutColumn(dataType = DataType.CHAR_50)
    private String position;
}
