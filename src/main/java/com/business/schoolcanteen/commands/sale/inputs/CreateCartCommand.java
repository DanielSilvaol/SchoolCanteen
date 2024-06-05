package com.business.schoolcanteen.commands.sale.inputs;

import com.business.schoolcanteen.entity.enums.PaymentTypeEnum;
import lombok.Data;
import java.util.List;

@Data
public class CreateCartCommand {
    private Long customerId;
    private List<CreateSaleCommand> items;
    private PaymentTypeEnum paymentType;
}
