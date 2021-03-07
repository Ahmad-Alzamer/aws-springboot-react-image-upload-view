package com.alzamer.ahmad.awsSpringbootReactImage.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class S3Service
{
    private final String bucketName;
    private final AmazonS3 s3Client;
    public S3Service(@Value("${custom.bucket-name}") String bucketName, AmazonS3 s3Client)
    {
        this.bucketName = bucketName;
        this.s3Client = s3Client;
    }


    public List<String> listBucketContent()
    {

        log.info("recived request to list content of bucket");
        s3Client.listObjects(this.bucketName)
                .getObjectSummaries().stream()
                .forEach(System.out::println);
        return s3Client.listObjects(this.bucketName)
                .getObjectSummaries().stream()
                .map(s3ObjectSummary -> s3ObjectSummary.getKey())
                .collect(Collectors.toList());
    }
    public void updateToBucket(String fileName, Optional<Map<String,String>> metaData, InputStream inputStream)
    {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        metaData.ifPresent( map -> map.forEach(objectMetadata::addUserMetadata) );

        s3Client.putObject(this.bucketName,fileName,inputStream,objectMetadata);
    }
}
