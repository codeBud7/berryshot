package com.codebud7.berryshot.properties;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Reloadable;

/**
 * Created by s.puskeiler on 25.03.16.
 */
public interface RaspberryProperties extends Reloadable
{
    @Config.Key("raspberry.raspistill.path")
    @Config.DefaultValue("/opt/vc/bin/raspistill")
    String getRaspistillPath();

    @Config.Key("raspberry.raspistill.timeout")
    @Config.DefaultValue("2000")
    Integer getRaspistillTimeout();

    @Config.Key("raspberry.picture.quality")
    @Config.DefaultValue("100")
    Integer getPictureQuality();

    @Config.Key("raspberry.picture.width")
    @Config.DefaultValue("1024")
    Integer getPictureWidth();

    @Config.Key("raspberry.picture.height")
    @Config.DefaultValue("768")
    Integer getPictureHeight();

    @Config.Key("raspberry.picture.output.path")
    @Config.DefaultValue(".")
    String getOutputPath();

    @Config.Key("raspberry.picture.name")
    @Config.DefaultValue("berryshot.jpg")
    String getPictureName();

    @Config.Key("raspberry.picture.encoding")
    @Config.DefaultValue("jpg")
    String getPictureEncoding();
}
