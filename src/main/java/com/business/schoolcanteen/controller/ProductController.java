package com.business.schoolcanteen.controller;

import com.business.schoolcanteen.commands.produtct.inputs.CreateProductCommand;
import com.business.schoolcanteen.service.product.CreateProductAction;
import com.business.schoolcanteen.service.product.ListProductAction;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final CreateProductAction createProductAction;
    private final ListProductAction listProductAction;
    @PostMapping("/register")
    public ResponseEntity<?> registerProduct(@RequestParam String name, @RequestParam double costPrice, @RequestParam double salePrice) {
        CreateProductCommand command = CreateProductCommand.builder()
                .name(name)
                .salePrice(salePrice)
                .costPrice(costPrice)
                .build();
        createProductAction.execute(command);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchProduct(@RequestParam String query) {
        return ResponseEntity.ok(listProductAction.findAll(query));
    }
}
