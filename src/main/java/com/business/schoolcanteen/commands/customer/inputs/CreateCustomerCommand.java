package com.business.schoolcanteen.commands.customer.inputs;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateCustomerCommand {
    @NotBlank(message = "O nome é obrigatório")
    private String name;
    private String cellphone;
}
