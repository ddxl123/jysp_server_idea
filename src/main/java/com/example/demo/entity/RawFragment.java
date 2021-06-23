package com.example.demo.entity;

import com.example.demo.TableGenerator.Annotations.*;
import com.example.demo.TableGenerator.type.DataType;
import lombok.Data;

import java.math.BigInteger;
import java.sql.Timestamp;

@Data
@MappingTable
public class RawFragment {
    @MappingColumnPYID
    private BigInteger id;

    @MappingColumnAIID
    private BigInteger userAiid;

    @MappingColumnAIID
    private BigInteger fatherRawFragmentAiid;

    @MappingColumnAIID
    private BigInteger recommendRawRuleAiid;

    @MappingColumn(dataType = DataType.CHAR_20)
    private String title;

    @MappingColumnTimestamp
    private Timestamp createdAt;

    @MappingColumnTimestamp
    private Timestamp updatedAt;
}
