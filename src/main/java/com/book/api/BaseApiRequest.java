package com.book.api;

import com.book.base.BaseRequest;
import com.book.exception.ApiException;
import com.book.util.GsonUtil;

/**
 * web 请求规范类
 * 创建用户:     wangHui
 * 创建时间:     2017-03-14
 * Created by IntelliJ IDEA.
 */
public class BaseApiRequest extends BaseRequest {
    @Override
    public boolean checkUpdateParams() throws ApiException {
        return ApiUtils.checkUpdateParams(this);
    }

    @Override
    public void checkParams() throws ApiException {
        ApiUtils.checkParams(this);
    }

    @Override
    public String toString() {
        return GsonUtil.getSimpleGsonInstance().toJson(this);
    }
}
