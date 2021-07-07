package com.example.demo.entity;

import com.example.demo.util.tablegenerator.annotation.OutColumn;
import com.example.demo.util.tablegenerator.annotation.OutColumnAIID;
import com.example.demo.util.tablegenerator.annotation.OutTable;
import com.example.demo.util.tablegenerator.type.DataType;
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
public class RawFragment extends BaseEntity<RawFragment> {
    @OutColumnAIID
    private BigInteger userAiid;

    @OutColumnAIID
    private BigInteger fatherRawFragmentAiid;

    @OutColumnAIID
    private BigInteger recommendRawRuleAiid;

    @OutColumn(dataType = DataType.CHAR_20)
    private String title;
}
