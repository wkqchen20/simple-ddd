package com.liuxy.ddd.infrastructure.config;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.liuxy.ddd.domain.base.IdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuxy
 * @date 2022-01-19
 */
@Configuration
public class IdGeneratorConfig {

    @Bean
    public IdGenerator idGenerator() {
        return () -> IdWorker.getId();
    }

}
