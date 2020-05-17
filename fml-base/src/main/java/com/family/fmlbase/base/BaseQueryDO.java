package com.family.fmlbase.base;

import io.swagger.annotations.ApiModelProperty;

import java.beans.Transient;
import java.io.Serializable;

public class BaseQueryDO implements Serializable {

    @ApiModelProperty(
            value = "验证码",
            notes = "用户输入的验证码"
    )
    private String verify;
    @ApiModelProperty(
            value = "图形验证码ticket",
            notes = "如果校验的是图形验证码，则需要携带ticket"
    )
    private String ticket;
    @ApiModelProperty(
            value = "页码",
            notes = "默认值0"
    )
    private Integer pageNum;
    @ApiModelProperty(
            value = "数量",
            notes = "默认值20,建议不要超过100,有特殊需求,及时沟通"
    )
    private Integer pageSize;

    public BaseQueryDO() {
    }

    public Integer getPageNum() {
        if (this.pageNum == null) {
            this.pageNum = 0;
        }

        return this.pageNum;
    }

    public void setPageNum(Integer pageNum) {
        if (pageNum != null && pageNum < 0) {
            this.pageNum = 0;
        } else {
            this.pageNum = pageNum;
        }

    }

    public Integer getPageSize() {
        if (this.pageSize == null || this.pageSize <= 0) {
            this.pageSize = 20;
        }

        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize != null && pageSize > 0) {
            this.pageSize = pageSize;
        } else {
            this.pageSize = 20;
        }

    }

    @Transient
    public Integer getFirstRow() {
        return this.getPageNum() * this.getPageSize();
    }

    public String getVerify() {
        return this.verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

    public String getTicket() {
        return this.ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
