package com.tour.kuma.global.common.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorMessage {

    DATA_NOT_FOUND(404, "data not found"), //static final 생략되어있다.
    LOGIN_ERROR(400,"login error"),
    REGIST_ERROR(400,"regist error"),
    TEMPERARY_SERVER_ERROR(400,"server error"); //개발자가 반응 못한 에러인 경우

    private int status;
    private String message;

    ErrorMessage(int status, String message) {
        this.status = status;
        this.message = message;
    }
}