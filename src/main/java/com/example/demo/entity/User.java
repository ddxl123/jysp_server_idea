package com.example.demo.entity;

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
public class User extends BaseEntity<User> {
    @OutColumn(dataType = DataType.CHAR_20)
    private String username;

    @OutColumn(dataType = DataType.CHAR_20)
    private String password;

    @OutColumn(dataType = DataType.CHAR_50)
    private String email;
}
