package com.alzamer.ahmad.awsSpringbootReactImage.Service;

import com.alzamer.ahmad.awsSpringbootReactImage.Exception.UserNotFoundException;
import com.alzamer.ahmad.awsSpringbootReactImage.Model.User;
import com.alzamer.ahmad.awsSpringbootReactImage.Repositories.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService
{
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo)
    {
        this.userRepo = userRepo;
    }

    public List<User> getAllUsers()
    {
        List<User> users = new LinkedList<>();
        for(User user: userRepo.findAll())
        {
            users.add(user);
        }
        return users;
    }
    public Optional<User> findUser(Optional<String> email)
    {
        String _email= email.orElseThrow(()-> new IllegalArgumentException("No email was provided"));
        Optional<User> user= userRepo.findByEmail(_email);
        return user;
    }

    public void addUser(User user)
    {
        userRepo.save(user);
    }
    public void updateUser(Optional<User> user)
    {
        user.orElseThrow(() -> new IllegalArgumentException("No User object was provided"));

        Optional<User> loadedUserOptional = userRepo.findByEmail(user.get().getEmail());
        User loadedUser = loadedUserOptional.orElseThrow(() ->new UserNotFoundException(user));
        loadedUser.setFirstName(user.get().getFirstName());
        loadedUser.setLastName(user.get().getLastName());
        loadedUser.setDob(user.get().getDob());
        userRepo.save(loadedUser);
    }
}
