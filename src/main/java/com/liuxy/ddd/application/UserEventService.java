package com.liuxy.ddd.application;

import com.liuxy.ddd.domain.user.event.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @author liuxy
 * @date 2022-01-19
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserEventService {

    @EventListener(classes = UserCreatedEvent.class)
    public void userCreated(UserCreatedEvent event) {
        log.info("new user:{} register", event.getUserId());
    }
}
