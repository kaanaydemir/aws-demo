package com.kaanaydemir.demo.aws.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.comprehend.AmazonComprehend;
import com.amazonaws.services.comprehend.AmazonComprehendClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ComprehendConfiguration {


    @Bean
    public AmazonComprehend createAmazonComprehend(AWSStaticCredentialsProvider credentials) {
        AmazonComprehend comprehendClient = AmazonComprehendClientBuilder.standard()
                .withCredentials(credentials)
                .withRegion(Regions.US_EAST_1)
                .build();

        return comprehendClient;
    }
}
