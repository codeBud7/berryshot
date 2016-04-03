package com.codebud7.berryshot.service;

import com.codebud7.berryshot.properties.ApplicationProperties;
import com.codebud7.berryshot.service.dropbox.DropboxService;
import com.codebud7.berryshot.service.dropbox.UploadFailedException;
import java.io.IOException;
import org.aeonbits.owner.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by s.puskeiler on 25.03.16.
 */
@Component
public class SyncService
{
    private final Logger LOGGER = LoggerFactory.getLogger(SyncService.class);
    private final ApplicationProperties applicationProperties = ConfigFactory.create(ApplicationProperties.class);

    @Autowired
    private RaspiStillService raspiStillService;

    @Autowired
    private DropboxService dropboxService;


    @Scheduled(fixedDelay = 60000)
    public void takeDropboxPicture()
    {
        if (this.applicationProperties.isSchedulerEnabled())
        {
            try
            {
                final String fileName = this.raspiStillService.takePicture();
                this.dropboxService.uploadFileV2(fileName);
            }
            catch (final UploadFailedException | IOException e)
            {
                this.LOGGER.error(e.toString());
            }
        }
    }
}
