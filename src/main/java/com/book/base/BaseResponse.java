package com.book.base;

import com.book.exception.ApiException;
import com.book.exception.BaseException;
import com.book.exception.ServiceException;

import java.io.Serializable;

/**
 * Created by yunpengsha on 2017/3/14.
 */
public abstract class BaseResponse implements Serializable {

    /**
     * 获取失败的json
     *
     * @param subCode
     * @param subMessage
     * @return
     */
    public abstract void setUnKnowErrorResponse(Integer subCode, String subMessage);

    /**
     * 获取失败的json
     *
     * @param errorCode
     * @param errorMessage
     * @param subCode
     * @param subMessage
     * @return
     */
    public abstract void setErrorResponse(Integer errorCode, String errorMessage, Integer subCode, String subMessage);

    /**
     * 参数校验失败错误
     *
     * @param e
     * @return
     */
    public abstract void setErrorResponse(BaseException e);

    /**
     * 参数校验失败错误
     *
     * @param e
     * @return
     */
    protected abstract void setErrorResponse(ApiException e);

    /**
     * 服务层抛出错误转换成接口对应的错误信息
     *
     * @param e
     * @return
     */
    protected abstract void setErrorResponse(ServiceException e);


//  /**
//   * 转换成功的json
//   *
//   * @return
//   */
//  public abstract String getSuccessResponse();
}
