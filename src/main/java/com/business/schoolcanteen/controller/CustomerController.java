package com.business.schoolcanteen.controller;

import com.business.schoolcanteen.commands.customer.inputs.CreateCustomerCommand;
import com.business.schoolcanteen.service.customer.CreateCustomerAction;
import com.business.schoolcanteen.service.customer.ListCustomerAction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CreateCustomerAction createCustomerAction;
    private final ListCustomerAction listCustomerAction;

    @GetMapping()
    public String showCustomers(Model model) {
        model.addAttribute("customers", listCustomerAction.findAll(""));
        return "customers";
    }

    @GetMapping("/create-customer")
    public String showCreateCustomers() {
        return "/create-customer";
    }

    @PostMapping("/register")
    public String createCustomer(CreateCustomerCommand command) {
        createCustomerAction.createCustomer(command);
        return "redirect:/customers";
    }

    @GetMapping("/search")
    public String searchCustomers(@RequestParam String query, Model model) {
        model.addAttribute("customers", listCustomerAction.findAll(query));
        return "customers";
    }
}
