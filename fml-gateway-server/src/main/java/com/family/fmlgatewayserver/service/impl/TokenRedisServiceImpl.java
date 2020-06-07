package com.family.fmlgatewayserver.service.impl;
import com.family.fmlbase.base.CommonResult;
import com.family.fmlgatewayserver.service.TokenRedisService;
import com.family.fmlgatewayserver.wrapper.RedisWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class TokenRedisServiceImpl implements TokenRedisService {
    @Autowired
    private RedisWrapper redisWrapper;

    @Override
    public CommonResult<Boolean> isExit(String Key, String value) {
        CommonResult<Boolean> result = new CommonResult<>();
        result.setResult(true);
        String data = redisWrapper.getCache(Key);
        if (data == null) {
            result.setResult(false);
            result.setMessage("登录失效！");
            return result;
        }
        if (!StringUtils.pathEquals(value, data)) {
            result.setResult(false);
            result.setMessage("账号已在其他设备登录！");
            return result;
        }
        return result;
    }
}
