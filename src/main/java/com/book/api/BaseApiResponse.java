package com.book.api;

import com.book.base.BaseResponse;
import com.book.base.ResponseErrorCode;
import com.book.exception.ApiException;
import com.book.exception.BaseException;
import com.book.exception.ServiceException;
import com.book.util.GsonUtil;

/**
 * api返回规范类
 * 创建用户:     wangHui
 * 创建时间:     2017-03-14
 * Created by IntelliJ IDEA.
 */
public class BaseApiResponse<T> extends BaseResponse {

    private Integer status = 0;

    private Integer errorCode;

    private String errorMessage;

    private Integer subCode;

    private String subMessage;

    private T data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    private void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getSubCode() {
        return subCode;
    }

    public void setSubCode(Integer subCode) {
        this.subCode = subCode;
    }

    public String getSubMessage() {
        return subMessage;
    }

    public void setSubMessage(String subMessage) {
        this.subMessage = subMessage;
    }

    @Override
    public void setUnKnowErrorResponse(Integer subCode, String subMessage) {
        this.status = 1;
        this.errorCode = ResponseErrorCode.UNKNOWN_ERROR;
        this.errorMessage = ResponseErrorCode.UNKNOWN_ERROR_MESSAGE;
        this.subCode = subCode;
        this.subMessage = subMessage;
    }

    @Override
    public void setErrorResponse(Integer errorCode, String errorMessage, Integer subCode, String subMessage) {
        this.status = 1;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.subCode = subCode;
        this.subMessage = subMessage;
    }

    @Override
    public void setErrorResponse(BaseException e) {
        if (e instanceof ServiceException) {
            setErrorResponse((ServiceException) e);
        } else if (e instanceof ApiException) {
            setErrorResponse((ApiException) e);
        }
    }

    @Override
    protected void setErrorResponse(ServiceException e) {
        this.status = 1;
        this.errorCode = ResponseErrorCode.SERVICE_ERROR;
        this.errorMessage = ResponseErrorCode.SERVICE_ERROR_MESSAGE;
        this.subCode = ApiException.getErrorCode(e.getMessage());
        this.subMessage = ApiException.getErrorMessage(e.getMessage());
    }

    @Override
    protected void setErrorResponse(ApiException e) {
        this.status = 1;
        this.errorCode = ResponseErrorCode.PARAM_ERROR;
        this.errorMessage = ResponseErrorCode.PARAM_ERROR_MESSAGE;
        this.subCode = ApiException.getErrorCode(e.getMessage());
        this.subMessage = ApiException.getErrorMessage(e.getMessage());
    }

    public void setErrorResponse(Exception e) {
        if (e instanceof ServiceException) {
            setErrorResponse((ServiceException) e);
        } else if (e instanceof ApiException) {
            setErrorResponse((ApiException) e);
        }
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return GsonUtil.getGsonInstance().toJson(this);
    }
}
