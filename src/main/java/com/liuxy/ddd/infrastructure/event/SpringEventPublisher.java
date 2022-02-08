package com.liuxy.ddd.infrastructure.event;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author liuxy
 * @date 2022-01-17
 */
@Component
public class SpringEventPublisher implements ApplicationContextAware, com.liuxy.ddd.domain.base.event.EventPublisher {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void publish(com.liuxy.ddd.domain.base.event.Event event) {
        applicationContext.publishEvent(event);
    }
}
