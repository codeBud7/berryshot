package com.codebud7.berryshot.controller;

import com.codebud7.berryshot.service.RaspiStillService;
import com.codebud7.berryshot.service.SyncService;
import com.codebud7.berryshot.service.dropbox.DropboxService;
import com.codebud7.berryshot.service.dropbox.UploadFailedException;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by s.puskeiler on 25.03.16.
 */
@RestController
class PictureController
{
    private final Logger LOGGER = LoggerFactory.getLogger(SyncService.class);

    @Autowired
    private RaspiStillService raspiStillService;

    @Autowired
    private DropboxService dropboxService;


    @RequestMapping(value = "/v1/picture/take", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void takePicture(@RequestParam(name = "enableDropboxUpload") final Boolean dropboxUpload) throws IOException
    {
        try
        {
            final String fileName = this.raspiStillService.takePicture();
            this.LOGGER.info("Took photo with name {}.", fileName);

            if (dropboxUpload)
            {
                this.dropboxService.uploadFileV2(fileName);
                this.LOGGER.info("Dropbox upload completed.");
            }
        }
        catch (final UploadFailedException e)
        {
            this.LOGGER.error(e.toString());
        }
    }
}
