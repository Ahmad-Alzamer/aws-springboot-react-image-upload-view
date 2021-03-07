package com.alzamer.ahmad.awsSpringbootReactImage.Repositories;

import com.alzamer.ahmad.awsSpringbootReactImage.Model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepo extends CrudRepository<User, Long>
{
    Optional<User> findByEmail(String email);
}
