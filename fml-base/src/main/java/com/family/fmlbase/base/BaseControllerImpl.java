package com.family.fmlbase.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseControllerImpl <T extends BaseDO, Q extends BaseQueryDO> {
    private static final Logger log = LoggerFactory.getLogger(BaseControllerImpl.class);
    public abstract BaseServiceAO<T, Q> getService();
}
