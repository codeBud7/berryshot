package com.codebud7.berryshot.service;

import com.codebud7.berryshot.model.RaspstillParams;
import com.codebud7.berryshot.properties.RaspberryProperties;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
            final String fileName = this.raspberryProperties.getOutputPath() + DateTime.now().toString(this.raspberryProperties.getDateFormat()) + this.raspberryProperties.getPictureName();

            final String command = this.raspberryProperties.getRaspistillPath() +
                " " + RaspstillParams.NOPREVIEW +
                " " + RaspstillParams.BURSTMODE +
                " " + RaspstillParams.TIMEOUT + " " + this.raspberryProperties.getRaspistillTimeout() +
                " " + RaspstillParams.WIDTH + " " + this.raspberryProperties.getPictureWidth() +
                " " + RaspstillParams.HEIGHT + " " + this.raspberryProperties.getPictureHeight() +
                " " + RaspstillParams.QUALITY + " " + this.raspberryProperties.getPictureQuality() +
                " " + RaspstillParams.ENCODING + " " + this.raspberryProperties.getPictureEncoding() +
                " " + RaspstillParams.EXPOSURE + " " + this.raspberryProperties.getPictureExposure() +
                " " + RaspstillParams.NAME + " " + fileName;

            this.LOGGER.debug(command);

            final Process process = Runtime.getRuntime().exec(command);
            process.waitFor();

            final BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            final StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null)
            {
                stringBuilder.append(line).append("\n");
            }

            this.LOGGER.debug(stringBuilder.toString());

            return fileName;
        }
        catch (final IOException | InterruptedException e)
        {
            this.LOGGER.error(e.toString());
            throw new IOException(e);
        }
    }
}
