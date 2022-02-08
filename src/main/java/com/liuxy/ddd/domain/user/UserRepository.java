package com.liuxy.ddd.domain.user;

import java.util.Optional;

/**
 * @author liuxy
 * @date 2022-01-17
 */
public interface UserRepository {

    Optional<User> userOf(Email email);

    Optional<User> userOf(UserId userId);

    void save(User user);

    void disableUser(User user);
}
