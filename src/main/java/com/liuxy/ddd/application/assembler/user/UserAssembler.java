package com.liuxy.ddd.application.assembler.user;

import com.liuxy.ddd.application.dto.user.UserListDTO;
import com.liuxy.ddd.domain.user.User;
import com.liuxy.ddd.infrastructure.dao.user.UserConverter;
import com.liuxy.ddd.infrastructure.dao.user.po.UserPO;
import com.liuxy.ddd.interfaces.commands.user.CreateUserCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author liuxy
 * @date 2022-01-19
 */
@Mapper
public interface UserAssembler {

    UserAssembler USER_ASSEMBLER = Mappers.getMapper(UserAssembler.class);

    default UserListDTO fromPO(UserPO userpo) {
        // TODO remove
        System.out.println(UserConverter.USER_CONVERTER.fromPO(userpo));
        return UserListDTO.builder()
                          .userId(userpo.getUserId())
                          .headImageUrl(userpo.getHeadImageUrl())
                          .nickname(userpo.getNickname())
                          .email(userpo.getEmail())
                          .build();
    }

    @Mappings({
            @Mapping(target = "othersInfo", expression = "java(new com.liuxy.ddd.domain.user.OthersInfo(command.getOthersInfo()))"),
            @Mapping(target = "email", expression = "java(com.liuxy.ddd.domain.user.Email.of(command.getEmail()))")
    })
    User fromCreateCommand(CreateUserCommand command);
}
