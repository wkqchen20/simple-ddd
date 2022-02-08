package com.liuxy.ddd.infrastructure.dao.user;

import com.liuxy.ddd.domain.user.User;
import com.liuxy.ddd.domain.base.JSONFormat;
import com.liuxy.ddd.infrastructure.dao.user.po.UserPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author liuxy
 * @date 2022-01-19
 */
@Mapper
public interface UserConverter extends JSONFormat {

    UserConverter USER_CONVERTER = Mappers.getMapper(UserConverter.class);

    @Mappings({
            @Mapping(target = "userId", expression = "java(user.getUserId().getId())"),
            @Mapping(target = "status", expression = "java(user.getStatus().status)"),
            @Mapping(target = "email", expression = "java(user.getEmail().getEmail())"),
            @Mapping(target = "othersInfo", expression = "java(fromObject(user.getOthersInfo()))"),
            @Mapping(target = "companyInfo", expression = "java(fromObject(user.getCompanyInfo()))"),
    })
    UserPO fromModel(User user);

    @Mappings({
            @Mapping(target = "userId", expression = "java(com.liuxy.ddd.domain.user.UserId.of(po.getUserId()))"),
            @Mapping(target = "status", expression = "java(com.liuxy.ddd.domain.base.enums.EnumsConverter.convert(po.getStatus(), com.liuxy.ddd.domain.user.UserStatus.class))"),
            @Mapping(target = "email", expression = "java(com.liuxy.ddd.domain.user.Email.of(po.getEmail()))"),
            @Mapping(target = "othersInfo", expression = "java(toObject(po.getOthersInfo(), com.liuxy.ddd.domain.user.OthersInfo.class))"),
            @Mapping(target = "companyInfo", expression = "java(toObject(po.getCompanyInfo(), com.liuxy.ddd.domain.user.CompanyInfo.class))"),
    })
    User fromPO(UserPO po);

}
