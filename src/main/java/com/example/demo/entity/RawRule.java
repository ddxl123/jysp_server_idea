package com.example.demo.entity;

import com.example.demo.tool.tablegenerator.annotation.OutColumnAIID;
import com.example.demo.tool.tablegenerator.annotation.OutTable;
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
public class RawRule extends BaseEntity<RawRule> {
    @OutColumnAIID
    private BigInteger userAiid;

    @OutColumnAIID
    private BigInteger fatherRawRuleAiid;
}
