package com.family.fmlgatewayserver.service;


import com.family.fmlbase.base.CommonResult;

public interface TokenRedisService {

    public CommonResult<Boolean> isExit(String mapKey, String valueKey);
}
