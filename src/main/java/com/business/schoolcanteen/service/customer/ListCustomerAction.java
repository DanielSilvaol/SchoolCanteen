package com.business.schoolcanteen.service.customer;

import com.business.schoolcanteen.entity.Customer;
import com.business.schoolcanteen.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ListCustomerAction {
    private final CustomerRepository customerRepository;

    public List<Customer> findAll(final String search) {
        return customerRepository.findAllByNameLikeIgnoreCase(search);
    }
}
