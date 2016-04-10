package com.codebud7.berryshot.controller;

import com.codebud7.berryshot.service.ExecutorService;
import java.io.IOException;
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
    @Autowired
    private ExecutorService executorService;


    @RequestMapping(value = "/v1/picture/take", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void takePicture(@RequestParam(name = "enableDropboxUpload") final Boolean dropboxUpload) throws IOException
    {
        this.executorService.takePicture(dropboxUpload);
    }
}
