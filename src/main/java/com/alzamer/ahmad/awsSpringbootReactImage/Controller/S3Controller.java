package com.alzamer.ahmad.awsSpringbootReactImage.Controller;

import com.alzamer.ahmad.awsSpringbootReactImage.Exception.FileNotProvidedException;
import com.alzamer.ahmad.awsSpringbootReactImage.Service.S3Service;
import org.springframework.http.HttpStatus;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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


    @GetMapping({"/**",""})
    public List<String> listBucketContent(HttpServletRequest request)
    {
        String pattern = (String)request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);

        String prefix = new AntPathMatcher().extractPathWithinPattern(pattern,
                request.getServletPath());
        return  s3Service.listBucketContent(Optional.ofNullable(prefix),Optional.empty());
    }

    @PostMapping
    public void saveToBucket( @RequestParam Optional<MultipartFile> file) throws IOException
    {
        MultipartFile _file=file.orElseThrow(FileNotProvidedException::new);
        s3Service.uploadToBucket(
                _file.getOriginalFilename(),
                _file.getContentType(),
                new BufferedInputStream(_file.getInputStream())
        );

    }

    @DeleteMapping({"/{fileName}",""})
    public void deleteFromBucket(@PathVariable(required = false) Optional<String> fileName)
    {
        fileName.ifPresent(s3Service::deleteFromBucket);
    }

}
