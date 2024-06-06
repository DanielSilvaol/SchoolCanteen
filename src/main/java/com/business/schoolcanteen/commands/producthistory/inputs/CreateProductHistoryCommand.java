package com.business.schoolcanteen.commands.producthistory.inputs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateProductHistoryCommand {
    private String productId;
    private String description;
}
