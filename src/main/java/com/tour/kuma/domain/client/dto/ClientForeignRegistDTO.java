package com.tour.kuma.domain.client.dto;

import com.tour.kuma.domain.client.entity.ClientForeign;

public record ClientForeignRegistDTO(String passportNumber, String foreignRegistNumber) {
    public ClientForeign toClientForeign() {
        return ClientForeign.builder()
                .foreignRegistNumber(foreignRegistNumber())
                .passportNumber(passportNumber())
                .build();

    }
}
