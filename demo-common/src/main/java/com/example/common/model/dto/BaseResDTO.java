package com.example.common.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * baseres
 * zhangyd
 * 2020/4/9 15:28
 */
@Data
public class BaseResDTO<T> implements Serializable {

    private static final long serialVersionUID = -3195113908885009489L;

    private Head head;

    private T body = null;

    public BaseResDTO(Head head, T body) {
        this.head = head;
        this.body = body;
    }

    public BaseResDTO(String code, String msg) {
        Head head = new Head();
        head.setCode(code);
        head.setMsg(msg);
        this.head = head;
    }


}
