package com.alzamer.ahmad.awsSpringbootReactImage.Exception;

public class BaseCustomException extends Exception
{
    public BaseCustomException()
    {
    }

    public BaseCustomException(String message)
    {
        super(message);
    }

    public BaseCustomException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public BaseCustomException(Throwable cause)
    {
        super(cause);
    }

    public BaseCustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
