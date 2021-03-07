package com.alzamer.ahmad.awsSpringbootReactImage.Bootstrap;

import com.alzamer.ahmad.awsSpringbootReactImage.Model.Image;
import com.alzamer.ahmad.awsSpringbootReactImage.Model.User;
import com.alzamer.ahmad.awsSpringbootReactImage.Repositories.UserRepo;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Component
public class MongoDbTestData implements CommandLineRunner
{
    private final UserRepo userRepo;
    private final Faker faker;
    public MongoDbTestData(UserRepo userRepo)
    {
        this.userRepo = userRepo;
        this.faker = new Faker(new Random(123));
    }

    @Override
    public void run(String... args) throws Exception
    {
        //delete users from previous run
        userRepo.deleteAll();
        int counter=0;
        List<User> users=new LinkedList<>();
        while(counter++<10)
        {
            User user=User.builder()
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .email(faker.bothify("???????###@gmail.com"))
                    .dob(faker.date().birthday(10,90).toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                    .image(Image.builder().name(faker.animal().name()+".jpg").createTimesamp(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()).tag(faker.color().name()).tag(faker.color().name()).path(faker.ancient().hero()).build())
                    .image(Image.builder().name(faker.animal().name()+".jpg").createTimesamp(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()).tag(faker.color().name()).tag(faker.color().name()).path(faker.ancient().hero()).build())
                    .build();
            users.add(user);
        }
        userRepo.saveAll(users);
        users.stream()
                .map(user -> String.format("save user to DB : %s",user))
                .forEach(System.out::println);

    }
}
