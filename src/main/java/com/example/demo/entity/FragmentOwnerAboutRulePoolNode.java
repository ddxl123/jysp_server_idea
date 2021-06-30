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
public class FragmentOwnerAboutRulePoolNode {
    @OutColumnPYID
    private BigInteger id;

    @OutColumnAIID
    private BigInteger userAiid;

    @OutColumnAIID
    private BigInteger pn_rule_pool_node_aiid;

    @OutColumnAIID
    private BigInteger raw_rule_aiid;

    @OutColumnTimestamp
    private Timestamp createdAt;

    @OutColumnTimestamp
    private Timestamp updatedAt;
}
