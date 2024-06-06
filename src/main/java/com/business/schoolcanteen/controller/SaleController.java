package com.business.schoolcanteen.controller;

import com.business.schoolcanteen.commands.sale.inputs.CreateCartCommand;
import com.business.schoolcanteen.service.sale.CreateSaleAction;
import com.business.schoolcanteen.service.sale.ListSaleAction;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {

    private final CreateSaleAction createSaleAction;
    private final ListSaleAction listSaleAction;

    @GetMapping("/search")
    public ResponseEntity<?> listSales(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        if (startDate == null)
            startDate = LocalDate.now();
        if (endDate == null)
            endDate = LocalDate.now();
        return ResponseEntity.ok(listSaleAction.findSalesByRegisterDate(startDate, endDate));
    }

    @PostMapping("/register")
    public ResponseEntity<String> createSale(@RequestBody CreateCartCommand command) {
        createSaleAction.execute(command);
        return ResponseEntity.ok("Sale created successfully");
    }
}
