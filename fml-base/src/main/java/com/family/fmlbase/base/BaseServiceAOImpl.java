package com.family.fmlbase.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class BaseServiceAOImpl<T extends BaseDO,Q extends BaseQueryDO>  implements BaseServiceAO<T, Q>{
    private static final Logger log = LoggerFactory.getLogger(BaseServiceAOImpl.class);


    public CommonResult<T> save(T param) {
        getDAO().save(param);
        return CommonResult.successReturn(param);
    }

    public CommonResult<Long> modify(T param) {
        return CommonResult.successReturn(Long.valueOf(getDAO().update(param)));
    }

    public CommonResult<Integer> count(Q param) {
        int count;
        try {
            count = getDAO().queryCount(param);
        } catch (Exception e) {
            log.error("mybatis exception", e);
            return CommonResult.errorReturn(CommonCode.SYSTEM_ERROR);
        }
        return CommonResult.successReturn(Integer.valueOf(count));
    }

    public CommonResult<T> queryFirst(Q param) {
        List<T> select;
        try {
            select = getDAO().queryList(param);
        } catch (Exception e) {
            log.error("mybatis exception", e);
            return CommonResult.errorReturn(CommonCode.SYSTEM_ERROR);
        }
        if (select.isEmpty())
            return CommonResult.errorReturn(CommonCode.NO_DATA);
        return CommonResult.successReturn(select.get(0));
    }

    public CommonResult<List<T>> query(Q param) {
        List<T> select;
        try {
            select = getDAO().queryList(param);
        } catch (Exception e) {
            log.error("mybatis exception", e);
            return CommonResult.errorReturn(CommonCode.SYSTEM_ERROR);
        }
        return CommonResult.successReturn(select);
    }

    public CommonResult<List<T>> queryAll(Q param) {
        param.setPageNum(Integer.valueOf(0));
        param.setPageSize(Integer.valueOf(2147483647));
        return query(param);
    }

    public CommonResult<Long> batchSave(List<T> param) {
        return CommonResult.successReturn(Long.valueOf(getDAO().batchSave(param)));
    }

    public CommonResult<T> get(Long primaryKey) {
        T result;
        try {
            result = getDAO().queryObject(primaryKey);
        } catch (Exception e) {
            log.error("mybatis exception", e);
            return CommonResult.errorReturn(CommonCode.SYSTEM_ERROR);
        }
        return CommonResult.successReturn(result);
    }

    public CommonResult<Long> remove(Long primaryKey) {
        return CommonResult.successReturn(Long.valueOf(getDAO().remove(primaryKey)));
    }

    public abstract BaseDAO<T, Q> getDAO();
}
