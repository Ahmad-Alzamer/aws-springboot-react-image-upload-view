package com.alzamer.ahmad.awsSpringbootReactImage.Model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Document(collection = "users")
@Getter@Setter@ToString@Builder@AllArgsConstructor@NoArgsConstructor
public class User
{

    @Id
    private ObjectId id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dob;
    @Singular
    private List<Image> images;
}
