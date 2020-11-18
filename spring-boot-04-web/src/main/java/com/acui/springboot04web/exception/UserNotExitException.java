package com.acui.springboot04web.exception;

public class UserNotExitException extends RuntimeException{
    public UserNotExitException() {
        super("用户不存在");
    }
}
