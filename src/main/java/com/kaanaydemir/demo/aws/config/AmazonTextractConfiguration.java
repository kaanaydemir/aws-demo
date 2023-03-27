package com.kaanaydemir.demo.aws.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.textract.AmazonTextract;
import com.amazonaws.services.textract.AmazonTextractClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonTextractConfiguration {


    @Bean
    public AmazonTextract createAmazonTextract(AWSStaticCredentialsProvider credentials) {
        AmazonTextract textractClient = AmazonTextractClientBuilder.standard()
                .withCredentials(credentials)
                .withRegion(Regions.US_EAST_1)
                .build();

        return textractClient;
    }
}
