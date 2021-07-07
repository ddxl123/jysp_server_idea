package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.demo.util.tablegenerator.annotation.OutColumnPYID;
import com.example.demo.util.tablegenerator.annotation.OutColumnTimestamp;
import lombok.Data;
import lombok.Getter;

import java.math.BigInteger;
import java.time.Instant;

@Data
public class BaseEntity<T extends BaseEntity<T>> {
    @OutColumnPYID
    @Getter
    @TableId(type = IdType.AUTO)
    private BigInteger id;

    @OutColumnTimestamp
    @Getter
    private Instant createdAt;

    @OutColumnTimestamp
    @Getter
    private Instant updatedAt;

    public T setId(BigInteger id) {
        this.id = id;
        return getThis();
    }

    public T setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
        return getThis();
    }

    public T setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return getThis();
    }

    @SuppressWarnings("unchecked")
    private T getThis() {
        return (T) this;
    }
}
