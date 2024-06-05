package com.business.schoolcanteen.controller;

import com.business.schoolcanteen.commands.customer.inputs.CreateCustomerCommand;
import com.business.schoolcanteen.service.customer.CreateCustomerAction;
import com.business.schoolcanteen.service.customer.ListCustomerAction;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CreateCustomerAction createCustomerAction;
    private final ListCustomerAction listCustomerAction;
    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@RequestParam CreateCustomerCommand command) {
        createCustomerAction.createCustomer(command);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchCustomer(@RequestParam String search) {
        return ResponseEntity.ok(listCustomerAction.findAll(search));
    }
}
