package com.alzamer.ahmad.awsSpringbootReactImage.Service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
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


    public List<String> listBucketContent(Optional<String> prefix,Optional<Integer> maxKeys)
    {
        log.info("received request to list content of bucket with prefix:"+prefix);
//        List<S3ObjectSummary> objectSummaries= s3Client.listObjects(this.bucketName).getObjectSummaries();
        //by using the ListObjectsV2Request instead of invoking listObjects directly, we can have more control.
        //for example, in the below code I limited the number of keys in the response to the maxKeys passed -default is 100 if nothing is passed in-
        //and get all with the same prefix -default is no prefix if nothing is passed in-
        //you can think of a bucket as a drive in the local windows -C or D drive- and think of prefix as a directory in the drive.
        //so, by fetching object with prefix, you can think of it as fetching the content of a directory in the drive.
        //however, keep in mind that the actual structure of the bucket is flat and the key for the file is the full path from the bucket to the file.

        ListObjectsV2Result s3ListObjectResult;
        String _prefix = prefix.orElse("");
        int _maxKeys = maxKeys.orElse(100);
        ListObjectsV2Request req = new ListObjectsV2Request().withBucketName(this.bucketName).withPrefix(_prefix).withMaxKeys(_maxKeys);
        List<S3ObjectSummary> objectSummaries = new LinkedList<>();

        do
        {
            s3ListObjectResult = s3Client.listObjectsV2(req);
            objectSummaries.addAll( s3ListObjectResult.getObjectSummaries());
            String continuationToken = s3ListObjectResult.getNextContinuationToken();
            req.setContinuationToken(continuationToken);
            log.trace("content of bucket {} : {}", this.bucketName, objectSummaries);
        }
        while(s3ListObjectResult.isTruncated());

        return objectSummaries.stream()
                .filter(s3ObjectSummary  -> !_prefix.equalsIgnoreCase(s3ObjectSummary.getKey()))
                .map(s3ObjectSummary -> s3ObjectSummary.getKey()+"::"+s3ObjectSummary.getLastModified().toInstant())
                .collect(Collectors.toList());
    }
    public List<String> listPresignedUrlForContent()
    {
        final Date expiration=Date.from(LocalDateTime.now().plusHours(1).atZone(ZoneId.systemDefault()).toInstant());


        log.info("received request to list content of bucket");
        List<S3ObjectSummary> results= s3Client.listObjects(this.bucketName).getObjectSummaries();
        log.trace("content of bucket {} : {}",this.bucketName,results);
        return results.stream()
                .map(s3ObjectSummary -> s3ObjectSummary.getKey())
                .map(key -> new GeneratePresignedUrlRequest(this.bucketName,key).withMethod(HttpMethod.GET).withExpiration(expiration))
                .map(s3Client::generatePresignedUrl)
                .map(url -> url.toString())
                .collect(Collectors.toList());
    }
    public void uploadToBucket(String fileName, String contentType,  InputStream inputStream)
    {
        log.info("received request to upload {}",fileName);
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.addUserMetadata("create_timestamp", LocalDateTime.now().toString());

        String[] fileNameParts= fileName.split("\\.");
        String key = fileNameParts[0]+UUID.randomUUID()+"."+fileNameParts[1];

        s3Client.putObject(this.bucketName,key,inputStream,objectMetadata);
        log.info("request completed and uploaded with key {}",key);
    }

    public void deleteFromBucket(String fileName)
    {
        log.info("received request to delete {}",fileName);
        s3Client.deleteObject(this.bucketName,fileName);
    }
}
