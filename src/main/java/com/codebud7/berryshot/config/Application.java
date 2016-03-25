package com.codebud7.berryshot.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackages = "com.codebud7.berryshot")
public class Application
{
    public static void main(final String[] args)
    {
        SpringApplication.run(Application.class, args);
    }
}
