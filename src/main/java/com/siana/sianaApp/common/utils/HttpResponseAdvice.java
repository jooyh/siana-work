package com.siana.sianaApp.common.utils;

import java.util.Map;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.siana.sianaApp.common.vo.ResultMap;

@ControllerAdvice
public class HttpResponseAdvice implements ResponseBodyAdvice<Object> {

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		Map result = (Map) body;
		if(!result.containsKey("code")) body = new ResultMap(body);
		return body;
	}

	@ResponseBody
	@ExceptionHandler(Exception.class)
    public ResponseEntity<ResultMap> exceptionHandler(Exception e) {
		e.printStackTrace();
        HttpStatus httpStatus = HttpStatus.OK;
        ResultMap rm = new ResultMap(null,e.hashCode(),e.getMessage());
        return new ResponseEntity<>(rm, httpStatus);
    }
}
