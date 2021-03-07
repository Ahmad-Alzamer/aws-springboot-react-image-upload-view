package com.alzamer.ahmad.awsSpringbootReactImage.Controller;

import com.alzamer.ahmad.awsSpringbootReactImage.Exception.UserNotFoundException;
import com.alzamer.ahmad.awsSpringbootReactImage.Model.User;
import com.alzamer.ahmad.awsSpringbootReactImage.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
@Slf4j
public class UserController
{
    private final UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers()
    {
        log.info("request received to get all users");
        return userService.getAllUsers();
    }

    @GetMapping("/{email}")
    public User getUser(@PathVariable String email)
    {
        log.info("request received to get user with ID: {}",email);
        return userService
                .findUser(Optional.of(email))
                .orElseThrow(() -> new UserNotFoundException(Optional.of(User.builder().email(email).build())));
    }

}
