package com.liuxy.ddd.application;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liuxy.ddd.application.dto.PageResult;
import com.liuxy.ddd.application.dto.user.UserListDTO;
import com.liuxy.ddd.domain.user.UserStatus;
import com.liuxy.ddd.infrastructure.dao.user.mapper.UserMapper;
import com.liuxy.ddd.infrastructure.dao.user.po.UserPO;
import com.liuxy.ddd.interfaces.queries.user.UserListQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.liuxy.ddd.application.assembler.user.UserAssembler.USER_ASSEMBLER;

/**
 * @author liuxy
 * @date 2022-01-19
 */
@Service
@RequiredArgsConstructor
public class UserQueryApplicationService {

    private final UserMapper userMapper;

    public PageResult<UserListDTO> users(UserListQuery query) {
        Page<UserPO> users = PageHelper.startPage(query.getPageNum(), query.getPageSize()).doSelectPage(() -> {
            LambdaQueryWrapper<UserPO> qw = new LambdaQueryWrapper<>();
            qw.eq(UserPO::getStatus, UserStatus.ACTIVE.status);
            userMapper.selectList(qw);
        });
        return PageResult.of(users, userPO -> USER_ASSEMBLER.fromPO(userPO));
    }
}
