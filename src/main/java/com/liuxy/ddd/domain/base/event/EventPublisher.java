package com.liuxy.ddd.domain.base.event;

/**
 * @author liuxy
 * @date 2022-01-19
 */
public interface EventPublisher {

    void publish(Event event);
}
