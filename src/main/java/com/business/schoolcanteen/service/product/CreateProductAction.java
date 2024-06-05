package com.business.schoolcanteen.service.product;

import com.business.schoolcanteen.commands.producthistory.inputs.CreateProductHistoryCommand;
import com.business.schoolcanteen.commands.produtct.inputs.CreateProductCommand;
import com.business.schoolcanteen.entity.Product;
import com.business.schoolcanteen.repository.ProductRepository;
import com.business.schoolcanteen.service.producthistory.CreateProductHistoryAction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CreateProductAction {
    private final ProductRepository productRepository;
    private final CreateProductHistoryAction createProductHistoryAction;

    public void execute(final CreateProductCommand command) {
        final Product product = Product.builder()
                .name(command.getName())
                .costPrice(command.getCostPrice())
                .salePrice(command.getSalePrice())
                .registerDate(LocalDateTime.now())
                .active(true)
                .build();
        productRepository.save(product);

        createProductHistoryAction.execute(
                CreateProductHistoryCommand.builder()
                        .productId(product.getId())
                        .description(String.format("Created product %s", product.getName()))
                        .build()
        );
    }
}
