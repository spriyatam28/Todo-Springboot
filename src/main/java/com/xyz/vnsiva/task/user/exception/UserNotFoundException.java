package com.xyz.vnsiva.task.user.exception;

import com.xyz.vnsiva.task.common.constants.ExceptionConstants;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super(ExceptionConstants.userIdNotFoundException + id);
    }
}
