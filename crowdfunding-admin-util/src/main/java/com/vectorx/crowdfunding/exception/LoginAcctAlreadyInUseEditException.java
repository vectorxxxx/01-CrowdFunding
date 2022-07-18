package com.vectorx.crowdfunding.exception;

/**
 * 登录失败异常类
 * @author vectorx
 */
public class LoginAcctAlreadyInUseEditException extends RuntimeException
{
    public LoginAcctAlreadyInUseEditException() {
    }

    public LoginAcctAlreadyInUseEditException(String message) {
        super(message);
    }

    public LoginAcctAlreadyInUseEditException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginAcctAlreadyInUseEditException(Throwable cause) {
        super(cause);
    }

    public LoginAcctAlreadyInUseEditException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
