package com.book.base;

/**
 * 返回给客户端的subErrorCode
 * Created with IntelliJ IDEA.
 * User: mac
 * Date: 17/3/14
 * Time: 下午1:23
 */
public class ResponseErrorCode {

    /**
     * 未知错误
     */
    public static final Integer UNKNOWN_ERROR = 1000;
    public static final String UNKNOWN_ERROR_MESSAGE = "未知错误";

    /**
     * 需要登录
     */
    public static final Integer LOGIN = 1001;
    public static final String LOGIN_MESSAGE = "登录token已过期,请重新登录";

    /**
     * 需要登录
     */
    public static final Integer LOGIN_WX = 10011;
    public static final String LOGIN_WX_MESSAGE = "请先绑定微信到手机号码";

    /**
     * 没有权限
     */
    public static final Integer PERMISSION_OFF = 1002;
    public static final String PERMISSION_OFF_MESSAGE = "没有访问权限";

    /**
     * 参数错误
     */
    public static final Integer PARAM_ERROR = 1003;
    public static final String PARAM_ERROR_MESSAGE = "请求参数错误";

    /**
     * 服务层抛出异常
     */
    public static final Integer SERVICE_ERROR = 1004;
    public static final String SERVICE_ERROR_MESSAGE = "服务层校验出错";


    /**
     * 检查信息有问题,返回的错误
     */
    public static final Integer CHECK_ERROR = 1005;
    public static final String CHECK_ERROR_MESSAGE = "校验请求不合法";


    /**
     * 服务异常,返回的错误
     */
    public static final Integer SYSTEM_ERROR = 1006;
    public static final String SYSTEM_ERROR_MESSAGE = "服务异常";


    /**
     * 超过3个月未修改密码
     */
    public static final Integer PWD_EXPIRE_ERROR = 1007;
    public static final String PWD_EXPIRE_ERROR_MESSAGE = "超过3个月未修改密码";

    /**
     * 用户信息缓存已失效或请求停滞或空闲超过1小时，请重新登录
     */
    public static final Integer REQUEST_IDLE_TIME_TIMEOUT_ERROR = 1008;
    public static final String REQUEST_IDLE_TIME_TIMEOUT_ERROR_MESSAGE = "用户信息缓存已失效或请求空闲超过1小时，请重新登录";

    /**
     * 输错密码超过3次，锁定账号30分钟
     */
    public static final Integer PWD_ERROR_THAN_THREE_TIMES = 1009;
    public static final String PWD_ERROR_THAN_THREE_TIMES_MESSAGE = "密码输入错误次数超过3次，请30分钟后重新登录";

}
