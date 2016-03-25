package com.codebud7.berryshot.model;

/**
 * Created by s.puskeiler on 25.03.16.
 */
public enum RaspstillParams
{
    NOPREVIEW("-n"),
    BURSTMODE("-bm"),
    TIMEOUT("-t"),
    WIDTH("-w"),
    HEIGHT("-h"),
    QUALITY("-q"),
    ENCODING("-e"),
    NAME("-o");

    private final String name;


    RaspstillParams(final String name)
    {
        this.name = name;
    }


    @Override
    public String toString()
    {
        return this.name;
    }
}
