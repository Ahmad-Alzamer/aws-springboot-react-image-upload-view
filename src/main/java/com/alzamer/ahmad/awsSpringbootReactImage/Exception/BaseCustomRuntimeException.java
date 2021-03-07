package com.alzamer.ahmad.awsSpringbootReactImage.Exception;

public class BaseCustomRuntimeException extends RuntimeException
{
    public BaseCustomRuntimeException()
    {
    }

    public BaseCustomRuntimeException(String message)
    {
        super(message);
    }

    public BaseCustomRuntimeException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public BaseCustomRuntimeException(Throwable cause)
    {
        super(cause);
    }

    public BaseCustomRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
