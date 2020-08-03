package com.book.exception;

/**
 * Created with IntelliJ IDEA.
 * User: mac
 * Date: 17/10/23
 * Time: 下午1:11
 */
public class BaseException extends Exception {
    public BaseException() {
        super();
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(Integer errorCode, String message) {
        super(errorCode + ":" + message);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }
}
