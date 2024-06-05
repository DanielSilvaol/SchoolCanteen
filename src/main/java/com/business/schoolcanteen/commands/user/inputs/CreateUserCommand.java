package com.business.schoolcanteen.commands.user.inputs;

import lombok.Data;

@Data
public class CreateUserCommand {
    private String name;
    private String username;
    private String password;
}
