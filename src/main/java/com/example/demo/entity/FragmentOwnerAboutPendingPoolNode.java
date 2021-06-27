package com.example.demo.entity;

import com.example.demo.tool.TableGenerator.Annotations.MappingColumnAIID;
import com.example.demo.tool.TableGenerator.Annotations.MappingColumnPYID;
import com.example.demo.tool.TableGenerator.Annotations.MappingColumnTimestamp;
import com.example.demo.tool.TableGenerator.Annotations.MappingTable;
import lombok.Data;

import java.math.BigInteger;
import java.sql.Timestamp;

@Data
@MappingTable
public class FragmentOwnerAboutPendingPoolNode {
    @MappingColumnPYID
    private BigInteger id;

    @MappingColumnAIID
    private BigInteger userAiid;

    @MappingColumnAIID
    private BigInteger rawFragmentAiid;

    @MappingColumnAIID
    private BigInteger pnPendingPoolNodeAiid;

    @MappingColumnAIID
    private BigInteger recommendRuleAiid;

    @MappingColumnTimestamp
    private Timestamp createdAt;

    @MappingColumnTimestamp
    private Timestamp updatedAt;
}
