package com.codebud7.berryshot.controller;

import com.codebud7.berryshot.service.SyncService;
import com.codebud7.berryshot.service.dropbox.DropboxService;
import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.users.FullAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by s.puskeiler on 25.03.16.
 */
@RestController
class AuthController
{
    private final Logger LOGGER = LoggerFactory.getLogger(SyncService.class);

    @Autowired
    private DropboxService dropboxService;


    @RequestMapping(value = "/v1/auth/info", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public FullAccount info()
    {
        try
        {
            final DbxClientV2 dropBoxClient = this.dropboxService.getDropBoxClientV2();
            return dropBoxClient.users().getCurrentAccount();
        }
        catch (final DbxException e)
        {
            this.LOGGER.error(e.toString());
        }

        return null;
    }
}
