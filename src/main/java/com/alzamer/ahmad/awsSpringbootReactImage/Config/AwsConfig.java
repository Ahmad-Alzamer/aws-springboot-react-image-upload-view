package com.alzamer.ahmad.awsSpringbootReactImage.Config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfig
{
    @Bean
    public AmazonS3 s3()
    {
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(DefaultAWSCredentialsProviderChain.getInstance().getCredentials()))
                .withRegion("us-east-1")
                .build();
    }
}
