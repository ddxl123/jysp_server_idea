package com.example.demo.entity;

import com.example.demo.TableGenerator.Annotations.MappingColumn;
import com.example.demo.TableGenerator.Annotations.MappingColumnPYID;
import com.example.demo.TableGenerator.Annotations.MappingTable;
import com.example.demo.TableGenerator.type.DataType;
import com.example.demo.TableGenerator.type.StorageType;
import lombok.Data;

import java.math.BigInteger;

@Data
@MappingTable
public class TestPerson {

    @MappingColumnPYID
    private BigInteger id;

    @MappingColumn(dataType = DataType.VARCHAR_500, storageTypes = {StorageType.NOT_NULL})
    private String personInfo;
}
