package com.liuxy.ddd.infrastructure.dao.user.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author liuxy
 * @date 2022-01-17
 */
@TableName("user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPO {
    private Long id;
    private Long userId;
    private Byte status;
    private String headImageUrl;
    private String email;
    private String nickname;
    private String phone;
    private String othersInfo;
    private String companyInfo;
    private Date createdTime;
    private Date modifiedTime;

}
