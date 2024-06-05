package com.business.schoolcanteen.repository;

import com.business.schoolcanteen.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(value = "SELECT c FROM Customer c WHERE UPPER(c.name) LIKE CONCAT('%', UPPER(?1), '%') ")
    List<Customer> findAllByNameLikeIgnoreCase(final String name);
}

