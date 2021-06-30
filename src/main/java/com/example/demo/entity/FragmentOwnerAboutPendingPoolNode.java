package com.example.demo.entity;

import com.example.demo.tool.tablegenerator.annotation.OutColumnAIID;
import com.example.demo.tool.tablegenerator.annotation.OutColumnPYID;
import com.example.demo.tool.tablegenerator.annotation.OutColumnTimestamp;
import com.example.demo.tool.tablegenerator.annotation.OutTable;
import lombok.Data;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @author 10338
 */
@Data
@OutTable
public class FragmentOwnerAboutPendingPoolNode {
    @OutColumnPYID
    private BigInteger id;

    @OutColumnAIID
    private BigInteger userAiid;

    @OutColumnAIID
    private BigInteger rawFragmentAiid;

    @OutColumnAIID
    private BigInteger pnPendingPoolNodeAiid;

    @OutColumnAIID
    private BigInteger recommendRuleAiid;

    @OutColumnTimestamp
    private Timestamp createdAt;

    @OutColumnTimestamp
    private Timestamp updatedAt;
}
