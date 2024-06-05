package com.business.schoolcanteen.service.customer;

import com.business.schoolcanteen.commands.customer.inputs.CreateCustomerCommand;
import com.business.schoolcanteen.entity.Customer;
import com.business.schoolcanteen.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateCustomerAction {
    private final CustomerRepository customerRepository;

    public void createCustomer(final CreateCustomerCommand command) {
        Customer customer = Customer.builder()
                .name(command.getName())
                .cellphone(command.getCellphone())
                .build();
        customerRepository.save(customer);
    }
}
