package com.family.fmlbase.base;

import java.util.List;

public interface BaseServiceAO<T,Q> {
    CommonResult<T> save(T paramT);

    CommonResult<Long> modify(T paramT);

    CommonResult<Integer> count(Q paramQ);

    CommonResult<List<T>> query(Q paramQ);

    CommonResult<T> queryFirst(Q paramQ);

    CommonResult<List<T>> queryAll(Q paramQ);

    CommonResult<Long> batchSave(List<T> paramList);

    CommonResult<T> get(Long paramLong);

    CommonResult<Long> remove(Long paramLong);
}
