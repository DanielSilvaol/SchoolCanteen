package com.business.schoolcanteen.controller;

import com.business.schoolcanteen.commands.user.inputs.CreateUserCommand;
import com.business.schoolcanteen.exception.UserExistsException;
import com.business.schoolcanteen.service.user.CreateUserAction;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserAction createUserAction;

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "create-user"; // Thymeleaf template name
    }

    @PostMapping("/register")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createUser(CreateUserCommand command) {
        try {
            createUserAction.createUser(command);
            return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
        } catch (UserExistsException e) {
            return new ResponseEntity<>("User with the provided username already exists", HttpStatus.CONFLICT);
        }
    }
}
