package com.business.schoolcanteen.service.sale;

import com.business.schoolcanteen.commands.sale.inputs.CreateCartCommand;
import com.business.schoolcanteen.commands.sale.inputs.CreateSaleCommand;
import com.business.schoolcanteen.entity.Customer;
import com.business.schoolcanteen.entity.Product;
import com.business.schoolcanteen.entity.Sale;
import com.business.schoolcanteen.repository.CustomerRepository;
import com.business.schoolcanteen.repository.ProductRepository;
import com.business.schoolcanteen.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CreateSaleAction {
    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    public void execute(CreateCartCommand command) {
        Optional<Customer> customerOptional = customerRepository.findById(command.getCustomerId());
        for (CreateSaleCommand saleCommand : command.getItems()) {
            Optional<Product> productOptional = productRepository.findById(saleCommand.getProductId());
            if (productOptional.isPresent() && customerOptional.isPresent()) {
                Product product = productOptional.get();
                Customer customer = customerOptional.get();
                Sale sale = Sale.builder()
                        .quantity(saleCommand.getQuantity())
                        .total(product.getSalePrice() * saleCommand.getQuantity())
                        .productPrice(product.getSalePrice())
                        .paymentType(command.getPaymentType())
                        .product(product)
                        .customer(customer)
                        .registerDate(LocalDateTime.now())
                        .build();
                saleRepository.save(sale);
            }
        }

    }
}
