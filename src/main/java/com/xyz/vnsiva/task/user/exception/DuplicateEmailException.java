package com.xyz.vnsiva.task.user.exception;

import com.xyz.vnsiva.task.common.Constants.ExceptionConstants;

public class DuplicateEmailException extends RuntimeException{
    public DuplicateEmailException(String email) {
        super(ExceptionConstants.duplicateEmailException);
    }
}
