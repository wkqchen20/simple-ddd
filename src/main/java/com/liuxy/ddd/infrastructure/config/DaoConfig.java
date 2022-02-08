package com.liuxy.ddd.infrastructure.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuxy
 * @date 2022-01-17
 */
@Configuration
@MapperScan("com.liuxy.ddd.infrastructure.dao")
public class DaoConfig {
}
