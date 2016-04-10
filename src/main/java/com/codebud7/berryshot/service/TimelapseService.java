package com.codebud7.berryshot.service;

import com.codebud7.berryshot.properties.RaspberryProperties;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.aeonbits.owner.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by s.puskeiler on 25.03.16.
 */
@Component
public class TimeLapseService
{
    private final Logger LOGGER = LoggerFactory.getLogger(TimeLapseService.class);


    public String makeTimeLapseVideo() throws IOException
    {
        try
        {
            final String command = "";

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

            return "";
        }
        catch (final IOException | InterruptedException e)
        {
            this.LOGGER.error(e.toString());
            throw new IOException(e);
        }
    }
}
