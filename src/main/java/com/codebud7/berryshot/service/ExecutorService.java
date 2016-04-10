package com.codebud7.berryshot.service;

import com.codebud7.berryshot.service.dropbox.DropboxService;
import com.codebud7.berryshot.service.dropbox.UploadFailedException;
import java.io.File;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by s.puskeiler on 10.04.16.
 */
@Component
public class ExecutorService
{
    private final Logger LOGGER = LoggerFactory.getLogger(ExecutorService.class);

    @Autowired
    private RaspiStillService raspiStillService;

    @Autowired
    private DropboxService dropboxService;


    public void takePicture(final Boolean withUpload)
    {
        try
        {
            final String fileName = this.raspiStillService.takePicture();
            this.LOGGER.info("Took photo with name {}.", fileName);

            if (withUpload)
            {
                this.dropboxService.uploadFileV2(fileName);
                this.LOGGER.info("Dropbox upload completed.");

                final File file = new File(fileName);
                file.delete();
                this.LOGGER.info("Removed local file copy.");
            }
        }
        catch (final IOException | UploadFailedException e)
        {
            this.LOGGER.error("Could not create picture successfully {}", e);
        }
    }
}
