package com.liuxy.ddd.interfaces.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WebResponse<T> {
    private static final int DEFAULT_CODE_SUCCESS = 0;
    private static final int DEFAULT_CODE_FAILURE = -1;
    private static final String DEFAULT_SUCCESS_MSG = "OK";
    private T data;
    private String message;
    private int code;

    public static <T> WebResponse<T> ok(T data) {
        return new WebResponse(data, "OK", 0);
    }

    public static <T> WebResponse<T> ok() {
        return ok(null);
    }

    public static <T> WebResponse<T> err(String message) {
        return err(-1, message);
    }

    public static <T> WebResponse<T> err(int errorCode, String errMsg) {
        return new WebResponse(null, errMsg, errorCode);
    }

    public static <T> WebResponse<T> err(int errorCode) {
        return err(errorCode, "");
    }

    public static <T> WebResponse<T> err(HttpStatus status, String errMsg) {
        return err(status.value(), errMsg);
    }

}