package com.liuxy.ddd.interfaces;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.liuxy.ddd.domain.base.BusinessException;
import com.liuxy.ddd.interfaces.dto.WebResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.stream.Collectors;

@ControllerAdvice
@Component
@Slf4j
public class ErrorAdvice {

    @ExceptionHandler({NoHandlerFoundException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public WebResponse requestHandlingNoHandlerFound(HttpServletRequest request, NoHandlerFoundException e) {
        this.log(404, request, e);
        return this.error(404, "url 不存在");
    }

    @ExceptionHandler({MissingServletRequestParameterException.class, ServletRequestBindingException.class, TypeMismatchException.class, HttpMessageNotReadableException.class, MissingServletRequestPartException.class, BindException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public WebResponse requestHandlingBadRequests(HttpServletRequest request, Exception e) {
        this.log(400, request, e);
        return this.error(400, "请求非法");
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public WebResponse validatedExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException e) {
        this.log(400, request, e);
        return error(400, e.getBindingResult());
    }

    @ExceptionHandler({AsyncRequestTimeoutException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public WebResponse requestHandlingNotAvailable(HttpServletRequest request, Exception e) {
        this.log(503, request, e);
        return this.error(503, "服务器无法提供服务");
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public WebResponse requestHandlingMethodNotSupported(HttpServletRequest request, Exception e) {
        this.log(405, request, e);
        return this.error(405, "http method 不被支持");
    }

    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public WebResponse requestHandlingHttpMediaTypeNotSupported(HttpServletRequest request, Exception e) {
        this.log(415, request, e);
        return this.error(415, "不支持的媒体类型");
    }

    @ExceptionHandler({NumberFormatException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public WebResponse requestHandlingNumberFormatException(HttpServletRequest request, Exception e) {
        this.log(400, request, e);
        return this.error(400, "请求非法");
    }

    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public WebResponse requestHandlingHttpMediaTypeNotAcceptable(HttpServletRequest request, Exception e) {
        this.log(406, request, e);
        return this.error(406, "媒体类型被拒绝");
    }

    @ExceptionHandler({MissingPathVariableException.class, ConversionNotSupportedException.class, HttpMessageNotWritableException.class, Exception.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public WebResponse requestHandlingInternalError(HttpServletRequest request, Exception e) {
        this.log(500, request, e);
        return this.error(500, "服务器内部错误");
    }

    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public WebResponse requestHandlingNoHandlerFound(HttpServletRequest request, BusinessException e) {
        this.log(-1, request, e);
        return this.error(-1, e.getMessage());
    }

    protected void log(int code, HttpServletRequest request, Exception e) {
        try {
            ImmutableMap.Builder<String, Object> headerBuilder = ImmutableMap.builder();
            Enumeration headerNames = request.getHeaderNames();

            while (headerNames.hasMoreElements()) {
                String key = (String) headerNames.nextElement();
                headerBuilder.put(key, request.getHeader(key));
            }

            ImmutableMap<String, Object> headers = headerBuilder.build();
            ImmutableMap.Builder<String, Object> req = ImmutableMap.<String, Object>builder().put("uri", request.getRequestURI()).put("header", headers).put("host", request.getRemoteAddr()).put("code", code);
            if (request.getCookies() != null) {
                req.put("cookie", request.getCookies());
            }

            ImmutableMap<String, Object> reqMap = req.build();
            log.error("\n" + JSONObject.toJSONString(reqMap), e);
        } catch (Exception ex) {
            log.error("fail to jsonify HttpServletRequest", ex);
        }

    }

    protected WebResponse error(int code, String errorMsg) {
        return WebResponse.err(code, errorMsg);
    }

    public static <T> WebResponse<T> error(int code, Errors bindingResult) {
        String resultMessage;
        try {
            resultMessage = bindingResult.getFieldErrors().stream().map((error) -> {
                return error.getField() + ":" + error.getDefaultMessage();
            }).collect(Collectors.joining(";"));
        } catch (Exception var4) {
            log.error("bindingResult parse message error! bindingResult:{} reason:{}", bindingResult, var4);
            resultMessage = "internal error!";
        }

        return WebResponse.err(code, resultMessage);
    }
}