package com.liuxy.ddd.domain.user.event;

import com.liuxy.ddd.domain.base.event.Event;
import com.liuxy.ddd.domain.user.UserId;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author liuxy
 * @date 2022-01-19
 */
@AllArgsConstructor
@Getter
public class UserCreatedEvent extends Event {
    private final UserId userId;
}
