package com.liuxy.ddd.interfaces.commands.user;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author liuxy
 * @date 2022-01-19
 */
@Data
public class CreateUserCompanyInfoCommand {

    @Length(max = 30)
    private String department;
    @Length(max = 30)
    private String position;

}
