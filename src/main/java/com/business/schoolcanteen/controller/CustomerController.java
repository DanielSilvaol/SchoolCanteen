package com.business.schoolcanteen.controller;

import com.business.schoolcanteen.commands.customer.inputs.CreateCustomerCommand;
import com.business.schoolcanteen.service.customer.CreateCustomerAction;
import com.business.schoolcanteen.service.customer.ListCustomerAction;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CreateCustomerAction createCustomerAction;
    private final ListCustomerAction listCustomerAction;
    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@Valid @RequestBody CreateCustomerCommand command) {
        createCustomerAction.createCustomer(command);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchCustomer(@RequestParam(required = false) String search) {
        return ResponseEntity.ok(listCustomerAction.findAll(search));
    }
}
