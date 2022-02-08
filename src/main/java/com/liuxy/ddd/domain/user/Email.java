package com.liuxy.ddd.domain.user;

import lombok.Getter;

import java.util.Objects;

/**
 * @author liuxy
 * @date 2022-01-17
 */
@Getter
public class Email {
    private final String email;

    public Email(String email) {
        if (Objects.isNull(email)) {
            throw new IllegalArgumentException("require email");
        }
        if (!valid(email)) {
            throw new IllegalArgumentException("邮件格式错误");
        }
        this.email = email;
    }

    public static Email of(String email) {
        return new Email(email);
    }

    private boolean valid(String email) {
        return true;
    }

    @Override
    public String toString() {
        return email;
    }
}
