package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

public class BaseServiceImpl<M extends BaseMapper<E>, E> extends ServiceImpl<M, E> {

    /**
     * 存在更新，不存在则插入。
     * <p>
     * 1. 存在时更新：
     * <p>
     * - 对谁进行更新：会对从数据库中查找出的 column 的值为 val 的全部 row 进行更新操作。
     * <p>
     * - 更新内容：更新内容为 updateWrapper 所 set 的内容。
     * <p>
     * 2. 不存在则插入，插入 insertEntity 的全部非 null 内容。
     *
     * @param insertEntity  插入的实体对象
     * @param lambdaUpdateWrapper 更新所需的 lambdaUpdateWrapper。
     * @param column        数据库的字段 column。
     * @param val           查找数据库字段的 column 对应的 val 值。
     * @return boolean
     */
    public boolean saveOrUpdate(
            E insertEntity,
            LambdaUpdateWrapper<E> lambdaUpdateWrapper,
            SFunction<E, ?> column,
            Object val
    ) {
        lambdaUpdateWrapper.eq(column, val);
        // 若这里传入 update 实体的话，实体为 null 的参数不会对数据库中的值修改成 null。
        return update(null, lambdaUpdateWrapper) || saveOrUpdate(insertEntity);
    }
}
