package com.alzamer.ahmad.awsSpringbootReactImage.Exception;

public class FileNotProvidedException extends BaseCustomRuntimeException
{
    public FileNotProvidedException()
    {
    }

    public FileNotProvidedException(String message)
    {
        super(message);
    }

    public FileNotProvidedException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public FileNotProvidedException(Throwable cause)
    {
        super(cause);
    }

    public FileNotProvidedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
