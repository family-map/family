package com.family.fmlbase.base;

import java.util.List;

public interface BaseDAO<T,Q> {
    long save(T paramT);

    long batchSave(List<T> paramList);

    long update(T paramT);

    long remove(Long paramLong);

    int queryCount(Q paramQ) throws Exception;

    List<T> queryList(Q paramQ) throws Exception;

    T queryObject(Long paramLong) throws Exception;
}
