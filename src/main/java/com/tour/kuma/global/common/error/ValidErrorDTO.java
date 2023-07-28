package com.tour.kuma.global.common.error;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ValidErrorDTO {

    private String field;
    private String message;
}
