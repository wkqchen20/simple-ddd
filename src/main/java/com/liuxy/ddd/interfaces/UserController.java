package com.liuxy.ddd.interfaces;

import com.liuxy.ddd.application.UserApplicationService;
import com.liuxy.ddd.application.UserQueryApplicationService;
import com.liuxy.ddd.application.dto.PageResult;
import com.liuxy.ddd.application.dto.user.UserListDTO;
import com.liuxy.ddd.domain.user.UserId;
import com.liuxy.ddd.interfaces.commands.user.CreateUserCommand;
import com.liuxy.ddd.interfaces.dto.WebResponse;
import com.liuxy.ddd.interfaces.queries.user.UserListQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author liuxy
 * @date 2022-01-19
 */
@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserApplicationService userApplicationService;
    private final UserQueryApplicationService userQueryApplicationService;

    @GetMapping("")
    public WebResponse<PageResult<UserListDTO>> users(UserListQuery query) {
        return WebResponse.ok(userQueryApplicationService.users(query));
    }

    @PostMapping("")
    public WebResponse<Long> createUser(@RequestBody @Valid CreateUserCommand command) {
        UserId user = userApplicationService.createUser(command);
        return WebResponse.ok(user.getId());
    }
}
