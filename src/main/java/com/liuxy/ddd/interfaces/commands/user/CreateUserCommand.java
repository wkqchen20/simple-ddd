package com.liuxy.ddd.interfaces.commands.user;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

/**
 * @author liuxy
 * @date 2022-01-19
 */
@Getter
@AllArgsConstructor
public class CreateUserCommand {

    @NotEmpty
    @Length(min = 2, max = 10)
    private String nickname;
    @NotEmpty
    @Length(min = 4, max = 40)
    private String email;
    @Length(max = 100)
    private String headImageUrl;
    @Length(max = 20)
    private String phone;
    private JSONObject othersInfo;
    @Valid
    private CreateUserCompanyInfoCommand companyInfo;

}
