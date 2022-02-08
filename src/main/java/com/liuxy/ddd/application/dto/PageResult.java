package com.liuxy.ddd.application.dto;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageSerializable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liuxy
 * @date 2022-01-19
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PageResult<T> extends PageSerializable<T> {

    public static <T> PageResult<T> of(long total, List<T> list) {
        PageResult result = new PageResult();
        result.setList(list);
        result.setTotal(total);
        return result;
    }

    public static <F, T> PageResult<T> of(Page<F> list, DTOConverter<F, T> converter) {
        return of(list.getTotal(), list.stream().map(converter::convert).collect(Collectors.toList()));
    }

    public interface DTOConverter<F, T> {

        T convert(F f);
    }
}
