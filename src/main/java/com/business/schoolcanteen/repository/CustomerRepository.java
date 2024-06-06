package com.business.schoolcanteen.repository;

import com.business.schoolcanteen.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    List<Customer> findAllByNameLikeIgnoreCase(String name);
}


