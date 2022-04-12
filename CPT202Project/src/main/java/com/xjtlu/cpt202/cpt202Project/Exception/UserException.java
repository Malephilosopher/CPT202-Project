package com.xjtlu.cpt202.cpt202Project.Exception;

public class UserException extends Exception {

    public UserException(ResultCode resultCode){
        super(String.valueOf(resultCode));

    }

    public enum ResultCode {
        PERMISSION_TOKEN_EXPIRED, PERMISSION_TOKEN_INVALID, PERMISSION_SIGNATURE_ERROR,USER_NOT_LOGGED_IN;
    }

}
