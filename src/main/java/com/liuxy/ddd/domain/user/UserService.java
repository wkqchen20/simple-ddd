package com.liuxy.ddd.domain.user;

import com.liuxy.ddd.domain.base.IdGenerator;
import com.liuxy.ddd.domain.base.event.EventPublisher;
import com.liuxy.ddd.domain.user.event.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author liuxy
 * @date 2022-01-17
 */
@Service
@RequiredArgsConstructor
public class UserService implements UserDomainService {

    private final UserRepository userRepository;
    private final EventPublisher eventPublisher;
    private final IdGenerator idGenerator;

    @Override
    public UserId register(User user) {
        Optional<User> existsUser = userRepository.userOf(user.getEmail());
        if (existsUser.isPresent()) {
            throw new EmailExistsException("email:" + user.getEmail() + " exists");
        }
        User newUser = user.create(UserId.of(idGenerator.generate()));
        userRepository.save(newUser);
        eventPublisher.publish(new UserCreatedEvent(newUser.getUserId()));
        return newUser.getUserId();
    }

    @Override
    public void disable(UserId userId) {
        Optional<User> optional = userRepository.userOf(userId);
        if (!optional.isPresent()) {
            throw new IllegalArgumentException("user not found");
        }
        User user = optional.get();
        user.disable();
        userRepository.disableUser(user);
    }


}
