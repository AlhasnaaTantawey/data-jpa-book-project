package com.global.book.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AmazonS3Config {
	 @Value("${aws.s3.accessKey}")
	    private String accessKey;

	    @Value("${aws.s3.secretKey}")
	    private String secretKey;


    @Bean
    AmazonS3 amazonS3() {
	        // Replace with your AWS access key and secret key
	        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);

	        // Create the S3 client
	        return AmazonS3ClientBuilder.standard()
	                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
	                .withRegion("us-west-2") // Replace with your desired region
	                .build();
	    }
}
