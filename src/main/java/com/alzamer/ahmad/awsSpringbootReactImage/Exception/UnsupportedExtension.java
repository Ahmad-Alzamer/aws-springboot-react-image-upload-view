package com.alzamer.ahmad.awsSpringbootReactImage.Exception;

public class UnsupportedExtension extends BaseCustomRuntimeException
{
    public UnsupportedExtension()
    {
    }

    public UnsupportedExtension(String message)
    {
        super(message);
    }

    public UnsupportedExtension(String message, Throwable cause)
    {
        super(message, cause);
    }

    public UnsupportedExtension(Throwable cause)
    {
        super(cause);
    }

    public UnsupportedExtension(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
