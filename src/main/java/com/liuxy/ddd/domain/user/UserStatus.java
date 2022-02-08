package com.liuxy.ddd.domain.user;

import com.liuxy.ddd.domain.base.enums.ToEnumTag;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**
 * @author liuxy
 * @date 2022-01-17
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToEnumTag(key = "status")
public enum UserStatus {

    ACTIVE((byte) 1),
    DISABLED((byte) 2);

    public final Byte status;
}
