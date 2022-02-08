package com.liuxy.ddd.application.dto.user;

import lombok.Builder;
import lombok.Getter;

/**
 * @author liuxy
 * @date 2022-01-19
 */
@Getter
@Builder
public class UserListDTO {

    private Long userId;
    private String email;
    private String nickname;
    private String headImageUrl;
}
