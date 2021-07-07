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
public class FragmentOwnerAboutMemoryPoolNode extends BaseEntity<FragmentOwnerAboutMemoryPoolNode> {
    @OutColumnAIID
    private BigInteger userAiid;

    @OutColumnAIID
    private BigInteger fragmentOwnerAboutPendingPoolNodeAiid;

    @OutColumnAIID
    private BigInteger pnMemoryPoolNodeAiid;

    @OutColumnAIID
    private BigInteger usingRuleAiid;
}
