package com.gouuse.checkin.bean;

/**
 * Created by zhengjiong on 16/4/3.
 */
public class Body<T> {
    public String code;
    public T data;

    @Override
    public String toString() {
        return "{" +
                "code='" + code + '\'' +
                ", data=" + data +
                '}';
    }
}
