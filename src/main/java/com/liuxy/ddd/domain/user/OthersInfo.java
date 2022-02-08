package com.liuxy.ddd.domain.user;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liuxy
 * @date 2022-01-17
 */
public class OthersInfo extends HashMap<String, Object> {

    public OthersInfo() {
    }

    public OthersInfo(Map<? extends String, ?> m) {
        if (m == null || m.isEmpty()) {
            return;
        }
        putAll(m);
    }
}
