package com.tour.kuma.global.common.error;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException{

    private final ErrorMessage errorMessage;
    private final String detailMessage;

    public ApiException(String message, ErrorMessage errorMessage) {
        this(message, errorMessage, "");
    }
    public ApiException(String message, ErrorMessage errorMessage, String detailMessage) {
        super(message);
        this.errorMessage = errorMessage;
        this.detailMessage = detailMessage;
    }

    public ApiException(ErrorMessage errorMessage) {
        this(errorMessage.getMessage(), errorMessage, "");
    }

    public ApiException (String message) {
        this(message, ErrorMessage.DATA_NOT_FOUND);
    }
    public ErrorMessage getError() {return this.errorMessage;}
}