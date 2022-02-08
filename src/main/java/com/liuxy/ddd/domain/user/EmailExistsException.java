package com.liuxy.ddd.domain.user;

import com.liuxy.ddd.domain.base.BusinessException;

/**
 * @author liuxy
 * @date 2022-01-20
 */
public class EmailExistsException extends BusinessException {

    public EmailExistsException(String message) {
        super(message);
    }

}
