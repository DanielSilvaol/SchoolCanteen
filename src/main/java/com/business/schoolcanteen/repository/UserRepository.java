package com.business.schoolcanteen.repository;

import com.business.schoolcanteen.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(final String username);
}
