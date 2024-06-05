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

@Controller
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {

    private final CreateSaleAction createSaleAction;
    private final ListSaleAction listSaleAction;
    private final ListProductAction listProductAction;
    private final ListCustomerAction listCustomerAction;

    @GetMapping
    public String listSales(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, Model model) {
        if (date == null)
            date = LocalDate.now();
        List<Sale> sales = listSaleAction.findAll(date);
        model.addAttribute("sales", sales);
        return "sales";
    }

    @GetMapping("/register")
    public String showSaleForm(Model model) {
        List<Product> products = listProductAction.findAll("");
        List<Customer> customers = listCustomerAction.findAll("");
        model.addAttribute("products", products);
        model.addAttribute("customers", customers);
        return "create-sale";
    }

    @PostMapping("/register")
    public ResponseEntity<String> createSale(@RequestBody CreateCartCommand command) {
        createSaleAction.execute(command);
        return ResponseEntity.ok("Sale created successfully");
    }
}
