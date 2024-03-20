package com.baltinfo.radius.application;

import com.baltinfo.radius.application.configuration.JobConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * <p>
 * </p>
 *
 * @author Lapenok Igor
 * @since 15.08.2018
 */
@Configuration
@Import(JobConfiguration.class)
public class JobApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(JobApplication.class);
    }
}

