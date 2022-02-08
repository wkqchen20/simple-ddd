package com.liuxy.ddd.domain.user;

import lombok.*;

import java.util.Date;

/**
 * @author liuxy
 * @date 2022-01-17
 */
@EqualsAndHashCode(of = "userId")
@Getter
@AllArgsConstructor
@ToString
public class User {
    @With(value = AccessLevel.PRIVATE)
    private final UserId userId;
    private UserStatus status;
    private String headImageUrl;
    private Email email;
    private String nickname;
    private String phone;
    private OthersInfo othersInfo;
    private CompanyInfo companyInfo;
    private Date createdTime;
    private Date modifiedTime;

    public User create(UserId userId) {
        User user = withUserId(userId);
        user.status = UserStatus.ACTIVE;
        user.createdTime = new Date();
        user.modifiedTime = new Date();
        return user;
    }

    public void disable() {
        if (status == UserStatus.DISABLED) {
            return;
        }
        status = UserStatus.DISABLED;
        modifiedTime = new Date();
    }

}
