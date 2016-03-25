package com.codebud7.berryshot.service;

import com.codebud7.berryshot.service.dropbox.DropboxService;
import com.codebud7.berryshot.service.dropbox.UploadFailedException;
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

    @Autowired
    private RaspiStillService raspiStillService;

    @Autowired
    private DropboxService dropboxService;


    @Scheduled(fixedDelay = 5000)
    public void takeDropboxPicture()
    {
        try
        {
            final String fileName = this.raspiStillService.takePicture();
            this.dropboxService.uploadFileV2(fileName);
        }
        catch (final UploadFailedException e)
        {
            this.LOGGER.error(e.toString());
        }
    }
}
