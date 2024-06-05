package com.business.schoolcanteen.commands.customer.inputs;

import lombok.Data;

@Data
public class CreateCustomerCommand {
    private String name;
    private String cellphone;
}
