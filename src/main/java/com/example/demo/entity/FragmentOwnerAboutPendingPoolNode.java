package com.example.demo.entity;

import com.example.demo.util.tablegenerator.annotation.OutColumnAIID;
import com.example.demo.util.tablegenerator.annotation.OutTable;
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
