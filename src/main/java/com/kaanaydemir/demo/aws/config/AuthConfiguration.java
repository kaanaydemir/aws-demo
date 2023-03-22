package com.kaanaydemir.demo.aws.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AuthConfiguration {
    @Value("${aws.access.key.id}")
    private String awsId;

    @Value("${aws.secret.access.key}")
    private String awsKey;

    @Bean
    public AWSStaticCredentialsProvider getAWSCredentials() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsId, awsKey);
        return new AWSStaticCredentialsProvider(awsCredentials);
    }
}
