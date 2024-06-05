package com.business.schoolcanteen.commands.produtct.inputs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateProductCommand {
    private String name;
    private Double costPrice;
    private Double salePrice;
}
