package com.business.schoolcanteen.controller;

import com.business.schoolcanteen.commands.produtct.inputs.CreateProductCommand;
import com.business.schoolcanteen.service.product.CreateProductAction;
import com.business.schoolcanteen.service.product.ListProductAction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final CreateProductAction createProductAction;
    private final ListProductAction listProductAction;

    @GetMapping()
    public String showProducts(Model model) {
        model.addAttribute("products", listProductAction.findAll(""));
        return "products";
    }

    @GetMapping("/create-product")
    public String createProduct() {
        return "create-product";
    }

    @PostMapping("/register")
    public String createProduct(@RequestParam String name, @RequestParam Double salePrice, @RequestParam Double costPrice) {
        CreateProductCommand command = CreateProductCommand.builder()
                .name(name)
                .salePrice(salePrice)
                .costPrice(costPrice)
                .build();
        createProductAction.execute(command);
        return "redirect:/products";
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam String query, Model model) {
        model.addAttribute("products", listProductAction.findAll(query));
        return "products";
    }
}
