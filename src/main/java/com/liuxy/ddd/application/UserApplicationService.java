package com.liuxy.ddd.application;

import com.liuxy.ddd.domain.user.User;
import com.liuxy.ddd.domain.user.UserId;
import com.liuxy.ddd.domain.user.UserService;
import com.liuxy.ddd.interfaces.commands.user.CreateUserCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.liuxy.ddd.application.assembler.user.UserAssembler.USER_ASSEMBLER;

/**
 * @author liuxy
 * @date 2022-01-17
 */
@Service
@RequiredArgsConstructor
public class UserApplicationService {

    private final UserService userService;

    public UserId createUser(CreateUserCommand command) {
        User user = USER_ASSEMBLER.fromCreateCommand(command);
        return userService.register(user);
    }
}
