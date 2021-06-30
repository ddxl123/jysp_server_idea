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
public class RawFragment {
    @OutColumnPYID
    private BigInteger id;

    @OutColumnAIID
    private BigInteger userAiid;

    @OutColumnAIID
    private BigInteger fatherRawFragmentAiid;

    @OutColumnAIID
    private BigInteger recommendRawRuleAiid;

    @OutColumn(dataType = DataType.CHAR_20)
    private String title;

    @OutColumnTimestamp
    private Timestamp createdAt;

    @OutColumnTimestamp
    private Timestamp updatedAt;
}
