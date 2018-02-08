package com.openplan.server.controller.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.openplan.server.http.response.JsonResponse;

@ControllerAdvice
public class ExceptionAdvisor {

    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public JsonResponse errorHandler(Exception ex) {
    	JsonResponse response = new JsonResponse();
    	response.setCode(1);
    	response.setMessage("UNDEFINED_ERROR");
        return response;
    }
}
