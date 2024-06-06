package com.business.schoolcanteen.controller;

import com.business.schoolcanteen.commands.user.inputs.CreateUserCommand;
import com.business.schoolcanteen.service.user.CreateUserAction;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserAction createUserAction;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createUser(@RequestBody @Valid CreateUserCommand command) {
        createUserAction.createUser(command);
        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    }
}
