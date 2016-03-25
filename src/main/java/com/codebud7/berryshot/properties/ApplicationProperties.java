package com.codebud7.berryshot.properties;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Reloadable;

/**
 * Created by s.puskeiler on 25.03.16.
 */
@Config.Sources({"classpath:application.properties"})
public interface ApplicationProperties extends Reloadable
{
    @Key("berryshot.scheduler.enable")
    @DefaultValue("true")
    Boolean isSchedulerEnabled();
}
