package com.codebud7.berryshot.service;

import com.codebud7.berryshot.properties.ApplicationProperties;
import org.aeonbits.owner.ConfigFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by s.puskeiler on 25.03.16.
 */
@Component
public class SyncService
{
    private final ApplicationProperties applicationProperties = ConfigFactory.create(ApplicationProperties.class);

    @Autowired
    private ExecutorService executorService;


    @Scheduled(cron = "0 0/1 * * * ?")
    public void takeDropboxPicture()
    {
        if (this.applicationProperties.isSchedulerEnabled())
        {
            this.executorService.takePicture(true);
        }
    }
}
