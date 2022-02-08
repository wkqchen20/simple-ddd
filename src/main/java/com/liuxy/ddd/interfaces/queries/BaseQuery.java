package com.liuxy.ddd.interfaces.queries;

import lombok.Data;

/**
 * @author liuxy
 * @date 2022-01-19
 */
@Data
public class BaseQuery {

    private int pageNum;
    private int pageSize;

    public BaseQuery() {
        this(1);
    }

    public BaseQuery(int pageNum) {
        this(pageNum, 15);
    }

    public BaseQuery(int pageNum, int pageSize) {
        if (pageNum <= 0) {
            pageNum = 1;
        }
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
}
