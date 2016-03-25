package com.codebud7.berryshot.service.dropbox;

/**
 * Created by s.puskeiler on 25.03.16.
 */
public class UploadFailedException extends Exception
{
    public UploadFailedException(final Throwable cause)
    {
        super(cause);
    }
}
