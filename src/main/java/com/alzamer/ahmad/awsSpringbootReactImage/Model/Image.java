package com.alzamer.ahmad.awsSpringbootReactImage.Model;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.time.LocalDateTime;
import java.util.List;


@Data@Builder
public class Image
{
    private String name;
    @Singular
    private List<String> tags;
    private LocalDateTime createTimesamp;
    private String path;
}
