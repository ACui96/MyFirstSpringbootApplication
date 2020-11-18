package com.acui.springboot04web.Contrller;

import com.acui.springboot04web.exception.UserNotExitException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author acui
 */
@ControllerAdvice
public class MyExceptionHandler {
    @ResponseBody
    @ExceptionHandler(UserNotExitException.class)
    public Map<String, Object> handleException(Exception e) {
        Map<String, Object> map = new HashMap<>();
        map.put("code","user.notExist");
        map.put("message", e.getMessage());
        return map;
    }

    /*@ExceptionHandler(UserNotExitException.class)
    public Map<String, Object> handleException(Exception e, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        //传入我们自己的状态码4xx、5xx
        request.setAttribute("javax.servlet.error.status_code",500);
        map.put("code","user.notExist");
        map.put("message", "用户出错了");
        request.setAttribute("ext",map);
        return map;
    }*/
}
