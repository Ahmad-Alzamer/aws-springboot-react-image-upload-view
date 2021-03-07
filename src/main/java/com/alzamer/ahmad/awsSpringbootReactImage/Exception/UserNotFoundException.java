package com.alzamer.ahmad.awsSpringbootReactImage.Exception;

import com.alzamer.ahmad.awsSpringbootReactImage.Model.User;

import java.util.Optional;

public class UserNotFoundException extends BaseCustomRuntimeException
{
    private Optional<User> user;

    public UserNotFoundException(Optional<User> user)
    {
        this.user = user;
    }

    @Override
    public String getMessage()
    {
        return "User not found. email: " + user
                .orElse(User.builder().email("Email not provided").build())
                .getEmail();
    }
}
