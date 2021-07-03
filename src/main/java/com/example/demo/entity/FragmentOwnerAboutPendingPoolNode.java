package com.example.demo.entity;

import com.example.demo.tool.tablegenerator.annotation.OutColumnAIID;
import com.example.demo.tool.tablegenerator.annotation.OutColumnPYID;
import com.example.demo.tool.tablegenerator.annotation.OutColumnTimestamp;
import com.example.demo.tool.tablegenerator.annotation.OutTable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigInteger;
import java.time.Instant;

/**
 * @author 10338
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@OutTable
public class FragmentOwnerAboutPendingPoolNode extends BaseEntity<FragmentOwnerAboutPendingPoolNode> {
    @OutColumnAIID
    private BigInteger userAiid;

    @OutColumnAIID
    private BigInteger rawFragmentAiid;

    @OutColumnAIID
    private BigInteger pnPendingPoolNodeAiid;

    @OutColumnAIID
    private BigInteger recommendRuleAiid;
}
