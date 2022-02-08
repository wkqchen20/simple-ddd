package com.liuxy.ddd.domain.base.enums;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.EnumSet;
import java.util.Objects;

/**
 * @author liuxy
 * @date 2022-01-19
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnumsConverter {

    public static <T extends Enum<T>> T convert(Object val, Class<T> aClass) {
        ToEnumTag tag = aClass.getAnnotation(ToEnumTag.class);
        if (tag == null) {
            throw new UnsupportedOperationException(aClass.getSimpleName() + " can not convert to enum without special ToEnumTag");
        }
        boolean errorWhenNotFound = tag.errorWhenNotFound();
        if (val == null) {
            if (errorWhenNotFound) {
                throw new NullPointerException("val can not be null");
            }
            return null;
        }
        EnumSet<T> es = EnumSet.allOf(aClass);
        // TODO cache
        Field field = field(aClass, tag.key());
        return es.stream().filter(e -> {
            Object fieldVal = fieldVal(e, field);
            return Objects.equals(fieldVal, val);
        }).findFirst().orElseGet(() -> {
            if (errorWhenNotFound) {
                throw new IllegalArgumentException("can not found enum:" + aClass.getSimpleName() + " with key:" + val);
            }
            return null;
        });
    }

    @SneakyThrows
    private static Field field(Class c, String fieldStr) {
        return c.getDeclaredField(fieldStr);
    }

    @SneakyThrows
    private static Object fieldVal(Object target, Field field) {
        field.setAccessible(true);
        return field.get(target);
    }
}
