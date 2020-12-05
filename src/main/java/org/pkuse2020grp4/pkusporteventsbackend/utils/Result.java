package org.pkuse2020grp4.pkusporteventsbackend.utils;

public class Result {
    private Integer code;
    private String msg;
    private Object data;

    Result(int code, String msg, Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    static public Result buildSuccessResult(String msg, Object data){
        return new Result(0, msg, data);
    }
    static public Result buildFailResult(String msg, Object data){
        return new Result(1, msg, data);
    }
}
