package com.family.fmlbase.base;

import com.gxy.service.base.CommonCode;
import lombok.Data;
import org.slf4j.MDC;

import java.io.Serializable;

@Data
public class CommonResult<T> implements Serializable {
    private Integer code = 0;
    private boolean success = false;
    private String message;
    private String traceId;
    private T result;
    private Integer total = 0;

    public CommonResult() {
        this.traceId = MDC.get("traceId");
    }

    public CommonResult(T result) {
        this.success = true;
        this.result = result;
        this.traceId = MDC.get("traceId");
    }

    public CommonResult(int code, String message) {
        if (code == 0) {
            this.success = true;
        }

        this.code = code;
        this.message = message;
        this.traceId = MDC.get("traceId");
    }

    public static <T> CommonResult<T> successReturn(T t) {
        return new CommonResult(t);
    }

    public static <T> CommonResult<T> errorReturn(String message) {
        return new CommonResult(CommonCode.CLIENT_ERROR.getCode(), message);
    }

    public static <T> CommonResult<T> errorReturn(int code, String message) {
        return new CommonResult(code, message);
    }

    public static <T> CommonResult<T> errorReturn(CommonCode def) {
        return new CommonResult(def.getCode(), def.getMessage());
    }

 /*   public Integer getCode() {
        return this.code;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public String getMessage() {
        return this.message;
    }

    public String getTraceId() {
        return this.traceId;
    }

    public T getResult() {
        return this.result;
    }

    public Integer getTotal() {
        return this.total;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }*/
}
