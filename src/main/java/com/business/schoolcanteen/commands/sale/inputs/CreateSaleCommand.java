package com.business.schoolcanteen.commands.sale.inputs;

import lombok.Data;

@Data
public class CreateSaleCommand {
    private int quantity;
    private String productId;
}
