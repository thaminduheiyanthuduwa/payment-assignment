package com.iiit.payment.payment.model;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class ResponseObj implements Serializable {

    private Integer status;
    private String msg;
    private Object object;

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public Object getObject() {
        return object;
    }
}
