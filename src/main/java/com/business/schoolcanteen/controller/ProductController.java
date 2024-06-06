package com.business.schoolcanteen.controller;

import com.business.schoolcanteen.commands.produtct.inputs.CreateProductCommand;
import com.business.schoolcanteen.service.product.CreateProductAction;
import com.business.schoolcanteen.service.product.ListProductAction;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final CreateProductAction createProductAction;
    private final ListProductAction listProductAction;
    @PostMapping("/register")
    public ResponseEntity<?> registerProduct(@RequestBody CreateProductCommand command) {
        createProductAction.execute(command);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchProduct(@RequestParam(required = false) String query) {
        return ResponseEntity.ok(listProductAction.findAll(query));
    }
}
