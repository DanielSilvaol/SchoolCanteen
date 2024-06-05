package com.business.schoolcanteen.service.user;

import com.business.schoolcanteen.commands.user.inputs.CreateUserCommand;
import com.business.schoolcanteen.entity.User;
import com.business.schoolcanteen.exception.UserExistsException;
import com.business.schoolcanteen.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CreateUserAction {
    private final UserRepository userRepository;

    public void createUser(final CreateUserCommand command) {
        this.validateUser(command.getUsername());
        final User user = User.builder()
                .name(command.getName())
                .username(command.getUsername())
                .password(command.getPassword())
                .created(LocalDateTime.now())
                .active(true)
                .build();
        userRepository.save(user);
    }

    private void validateUser(final String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        userOptional.ifPresent(user -> {
            throw new UserExistsException();
        });
    }
}
