package com.xyz.vnsiva.task.user.exception;

import com.xyz.vnsiva.task.common.Constants.ExceptionConstants;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super(ExceptionConstants.userIdNotFoundException + id);
    }
}
