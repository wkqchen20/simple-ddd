package com.liuxy.ddd.domain.user;

import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * @author liuxy
 * @date 2022-01-17
 */
@Value
@AllArgsConstructor
public class CompanyInfo {
    private final String department;
    private final String position;
}
