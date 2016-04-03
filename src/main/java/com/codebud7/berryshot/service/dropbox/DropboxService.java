package com.codebud7.berryshot.service.dropbox;

import com.codebud7.berryshot.properties.DropboxProperties;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.WriteMode;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Locale;
import org.aeonbits.owner.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by s.puskeiler on 25.03.16.
 */
@Component
public class DropboxService
{
    private final DropboxProperties dropboxProperties = ConfigFactory.create(DropboxProperties.class);
    private final Logger LOGGER = LoggerFactory.getLogger(DropboxService.class);

    private final DbxRequestConfig dbxRequestConfig;


    private DropboxService()
    {
        this.dbxRequestConfig = new DbxRequestConfig(this.dropboxProperties.getIdentifier(), Locale.getDefault().toString());
    }


    public DbxClientV2 getDropBoxClientV2()
    {
        return new DbxClientV2(this.dbxRequestConfig, this.dropboxProperties.getAuthCode());
    }


    public void uploadFileV2(final String fileName) throws UploadFailedException
    {
        try
        {
            final File file = new File(new File("."), fileName);
            try (InputStream fileInputStream = new FileInputStream(file))
            {
                final FileMetadata metadata = getDropBoxClientV2().files().uploadBuilder(this.dropboxProperties.getOutputPath())
                    .withMode(WriteMode.ADD)
                    .withClientModified(new Date())
                    .uploadAndFinish(fileInputStream);

                this.LOGGER.info(metadata.toStringMultiline());
            }
        }
        catch (final DbxException | IOException e)
        {
            this.LOGGER.error(e.toString());
            throw new UploadFailedException(e);
        }
    }
}
