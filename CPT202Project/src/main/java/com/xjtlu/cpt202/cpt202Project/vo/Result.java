package com.xjtlu.cpt202.cpt202Project.vo;
import lombok.Data;
@Data
public class Result {
    public Integer code; //200表示成功
    public String msg;  //message
    public Object data;

    private Result(){
    }

    private Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    private Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public static Result success(String msg, Object data) {
        return new Result(200, msg, data);
    }

    public static Result success(String msg) {
        return new Result(200, msg);
    }

    public static Result create(Integer code, String msg, Object data) {
        return new Result(code, msg, data);
    }

    public static Result create(Integer code, String msg) {
        return new Result(code, msg);
    }

}
