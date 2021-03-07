package com.alzamer.ahmad.awsSpringbootReactImage.Controller;

import com.alzamer.ahmad.awsSpringbootReactImage.Service.S3Service;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.*;

@RestController()
@RequestMapping("/api/s3Controller")
public class S3Controller
{
    private final S3Service s3Service;

    public S3Controller(S3Service s3Service)
    {
        this.s3Service = s3Service;
    }


    @GetMapping
    public List<String> listBucketContent()
    {
        return  s3Service.listBucketContent();
    }

    @GetMapping("/create")
    public void saveToBucket() throws FileNotFoundException
    {
        FileInputStream in= new FileInputStream("C:\\Users\\Ahmad Alzamer\\Desktop\\tut\\DevOps\\k8s\\k8s-commands.txt");
        Map<String,String> metaData=new HashMap<>();
        metaData.put("create_timestamp", LocalDateTime.now().toString());
        Optional<Map<String,String>> metaDataOptional= Optional.of(metaData);
        String fileName=UUID.randomUUID()+".txt";

        s3Service.updateToBucket(fileName,metaDataOptional,in);

    }

}
