package com.business.schoolcanteen.controller;

import com.business.schoolcanteen.commands.sale.inputs.CreateCartCommand;
import com.business.schoolcanteen.commands.sale.inputs.CreateSaleCommand;
import com.business.schoolcanteen.entity.Customer;
import com.business.schoolcanteen.entity.Product;
import com.business.schoolcanteen.entity.Sale;
import com.business.schoolcanteen.service.customer.ListCustomerAction;
import com.business.schoolcanteen.service.product.ListProductAction;
import com.business.schoolcanteen.service.sale.CreateSaleAction;
import com.business.schoolcanteen.service.sale.ListSaleAction;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {

    private final CreateSaleAction createSaleAction;
    private final ListSaleAction listSaleAction;

    @GetMapping
    public ResponseEntity<?> listSales(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        if (date == null)
            date = LocalDate.now();
        return ResponseEntity.ok(listSaleAction.findAll(date));
    }

    @PostMapping("/register")
    public ResponseEntity<String> createSale(@RequestBody CreateCartCommand command) {
        createSaleAction.execute(command);
        return ResponseEntity.ok("Sale created successfully");
    }
}
