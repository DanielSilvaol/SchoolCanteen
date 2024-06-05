package com.business.schoolcanteen.service.producthistory;

import com.business.schoolcanteen.commands.producthistory.inputs.CreateProductHistoryCommand;
import com.business.schoolcanteen.entity.ProductHistory;
import com.business.schoolcanteen.repository.ProductHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateProductHistoryAction {
    private final ProductHistoryRepository productHistoryRepository;

    public void execute(final CreateProductHistoryCommand command) {
        final ProductHistory productHistory = ProductHistory.builder()
                .productId(command.getProductId())
                .description(command.getDescription())
                .build();
        productHistoryRepository.save(productHistory);
    }
}
