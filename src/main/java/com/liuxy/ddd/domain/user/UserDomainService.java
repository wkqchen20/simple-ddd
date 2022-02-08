package com.liuxy.ddd.domain.user;

/**
 * @author liuxy
 * @date 2022-01-19
 */
public interface UserDomainService {

    UserId register(User user);

    void disable(UserId userId);
}
