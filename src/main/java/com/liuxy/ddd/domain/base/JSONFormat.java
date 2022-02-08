package com.liuxy.ddd.domain.base;

import com.alibaba.fastjson.JSON;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * @author liuxy
 * @date 2022-01-19
 */
public interface JSONFormat {

    default String fromObject(Object data) {
        return Optional.ofNullable(data)
                       .map(obj -> {
                           if (obj instanceof String) {
                               return (String) obj;
                           }
                           return JSON.toJSONString(obj);
                       })
                       .orElse("");
    }

    default <T> T toObject(String value, Class<T> tClass) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return JSON.parseObject(value, tClass);
    }
}
