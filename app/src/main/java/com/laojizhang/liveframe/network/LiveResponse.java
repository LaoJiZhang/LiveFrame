package com.laojizhang.liveframe.network;

import java.io.Serializable;

/**
 * 文件名称： LiveResponse
 * 作   者： guomaojian
 * 创建日期： 2017/06/06-19:00
 * 文件描述：
 * <p>
 */


public class LiveResponse<O extends Object> implements Serializable {

    private int resultcode;
    private String reason;
    private int error_code;
    private O result;

    public int getResultcode() {
        return resultcode;
    }

    public void setResultcode(int resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public O getResult() {
        return result;
    }

    public void setResult(O result) {
        this.result = result;
    }
}
