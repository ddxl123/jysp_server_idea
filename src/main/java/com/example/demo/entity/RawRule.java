package com.example.demo.entity;

import com.example.demo.TableGenerator.Annotations.MappingColumnAIID;
import com.example.demo.TableGenerator.Annotations.MappingColumnPYID;
import com.example.demo.TableGenerator.Annotations.MappingColumnTimestamp;
import com.example.demo.TableGenerator.Annotations.MappingTable;
import lombok.Data;

import java.math.BigInteger;
import java.sql.Timestamp;

@Data
@MappingTable
public class RawRule {
    @MappingColumnPYID
    private BigInteger id;

    @MappingColumnAIID
    private BigInteger userAiid;

    @MappingColumnAIID
    private BigInteger fatherRawRuleAiid;

    @MappingColumnTimestamp
    private Timestamp createdAt;

    @MappingColumnTimestamp
    private Timestamp updatedAt;
}
