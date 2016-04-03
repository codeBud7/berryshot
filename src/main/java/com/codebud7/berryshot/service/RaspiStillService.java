package com.codebud7.berryshot.service;

import com.codebud7.berryshot.model.RaspstillParams;
import com.codebud7.berryshot.properties.RaspberryProperties;
import java.io.IOException;
import org.aeonbits.owner.ConfigFactory;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by s.puskeiler on 25.03.16.
 */
@Component
public class RaspiStillService
{
    private final RaspberryProperties raspberryProperties = ConfigFactory.create(RaspberryProperties.class);
    private final Logger LOGGER = LoggerFactory.getLogger(RaspiStillService.class);


    public String takePicture() throws IOException
    {
        try
        {
            final String fileName = DateTime.now().toString("yyyy-MM-dd-hh-mm-ss-") + this.raspberryProperties.getPictureName();

            final String command = this.raspberryProperties.getRaspistillPath() +
                " " + RaspstillParams.NOPREVIEW +
                " " + RaspstillParams.BURSTMODE +
                " " + RaspstillParams.TIMEOUT + " " + this.raspberryProperties.getRaspistillTimeout() +
                " " + RaspstillParams.WIDTH + " " + this.raspberryProperties.getPictureWidth() +
                " " + RaspstillParams.HEIGHT + " " + this.raspberryProperties.getPictureHeight() +
                " " + RaspstillParams.QUALITY + " " + this.raspberryProperties.getPictureQuality() +
                " " + RaspstillParams.ENCODING + " " + this.raspberryProperties.getPictureEncoding() +
                " " + RaspstillParams.NAME + " " + fileName;

            this.LOGGER.debug(command);
            Runtime.getRuntime().exec(command);

            return fileName;
        }
        catch (final Exception e)
        {
            this.LOGGER.error(e.toString());
            throw e;
        }
    }
}
