package com.tour.kuma.global.common.error;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    private int status;

    private ZonedDateTime zoneDataTime;

    public ErrorResponse(ErrorMessage error) {
        this.message = error.getMessage();
        this.status = error.getStatus();
    }

    public static ErrorResponse of(ErrorMessage error ) {
        return new ErrorResponse(error);
    }
}