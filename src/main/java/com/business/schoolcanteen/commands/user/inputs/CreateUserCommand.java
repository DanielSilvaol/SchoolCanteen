package com.business.schoolcanteen.commands.user.inputs;

import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data

public class CreateUserCommand {
    @NotBlank(message = "O nome é obrigatório")
    private String name;

    @NotBlank(message = "O nome de usuário é obrigatório")
    private String username;

    @NotBlank(message = "A senha é obrigatória")
    private String password;
}
