package com.liuxy.ddd.infrastructure.dao.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liuxy.ddd.domain.user.Email;
import com.liuxy.ddd.domain.user.User;
import com.liuxy.ddd.domain.user.UserId;
import com.liuxy.ddd.domain.user.UserRepository;
import com.liuxy.ddd.infrastructure.dao.user.mapper.UserMapper;
import com.liuxy.ddd.infrastructure.dao.user.po.UserPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

import static com.liuxy.ddd.infrastructure.dao.user.UserConverter.USER_CONVERTER;

/**
 * @author liuxy
 * @date 2022-01-17
 */
@RequiredArgsConstructor
@Component
public class UserRepositoryImpl implements UserRepository {

    private final UserMapper userMapper;

    @Override
    public Optional<User> userOf(Email email) {
        UserPO userPO = userMapper.selectOne(new QueryWrapper<UserPO>().eq("email", email.getEmail()));
        return Optional.ofNullable(userPO).map(USER_CONVERTER::fromPO);
    }

    @Override
    public void save(User user) {
        UserPO userPO = _userOf(user.getUserId());
        if (Objects.isNull(userPO)) {
            userMapper.insert(USER_CONVERTER.fromModel(user));
        } else {
            UserPO val = USER_CONVERTER.fromModel(user);
            val.setId(userPO.getId());
            userMapper.updateById(val);
        }
    }

    @Override
    public void disableUser(User user) {
        userMapper.update(USER_CONVERTER.fromModel(user), new LambdaQueryWrapper<UserPO>().eq(UserPO::getUserId, user.getUserId().getId()));
    }

    @Override
    public Optional<User> userOf(UserId userId) {
        return Optional.of(_userOf(userId)).map(USER_CONVERTER::fromPO);
    }

    private UserPO _userOf(UserId userId) {
        return userMapper.selectOne(new LambdaQueryWrapper<UserPO>().eq(UserPO::getUserId, userId.getId()));
    }
}
