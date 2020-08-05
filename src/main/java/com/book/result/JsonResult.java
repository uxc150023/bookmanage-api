package com.book.result;

import lombok.Data;

@Data
public class JsonResult<T> {

    private T data;
    private String code;
    private String msg;
    private Integer status;

    /**
     * 若没有数据返回，默认状态码为 0，提示信息为“操作成功！”
     */
    public JsonResult() {
        this.code = "0";
        this.msg = "操作成功！";
        this.status = 0;
    }

    /**
     * 若没有数据返回，可以人为指定状态码和提示信息
     * @param code
     * @param msg
     */
    public JsonResult(String code, String msg, Integer status) {
        this.code = code;
        this.msg = msg;
        this.status = status;
    }

    /**
     * 有数据返回时，状态码为 0，默认提示信息为“操作成功！”
     * @param data
     */
    public JsonResult(T data) {
        this.data = data;
        this.status = 0;
        this.code = "0";
        this.msg = "操作成功！";
    }

    /**
     * 有数据返回，状态码为 0，人为指定提示信息
     * @param data
     * @param msg
     */
    public JsonResult(T data, String msg) {
        this.data = data;
        this.status = status;
        this.code = "0";
        this.msg = msg;
    }
}
