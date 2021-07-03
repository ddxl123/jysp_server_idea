package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.tool.tablegenerator.annotation.OutColumn;
import com.example.demo.tool.tablegenerator.annotation.OutTable;
import com.example.demo.tool.tablegenerator.type.DataType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author 10338
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@OutTable
public class EmailVerify extends BaseEntity<EmailVerify> {
    @OutColumn(dataType = DataType.CHAR_50)
    private String email;

    @OutColumn(dataType = DataType.INT_INTEGER)
    private Integer code;
}
