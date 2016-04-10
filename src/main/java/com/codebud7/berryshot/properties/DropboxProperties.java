package com.codebud7.berryshot.properties;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Reloadable;

/**
 * Created by s.puskeiler on 25.03.16.
 */
@Config.Sources({"classpath:dropbox.properties"})
public interface DropboxProperties extends Reloadable
{
    @Key("dropbox.auth.code")
    String getAuthCode();

    @Key("dropbox.identifier")
    @Config.DefaultValue("berryshot")
    String getIdentifier();

    @Config.Key("dropbox.output.path")
    @Config.DefaultValue("/")
    String getDropboxUploaderPath();
}
