package com.tour.kuma.domain.client.dto;

import com.tour.kuma.domain.client.entity.Client;
import com.tour.kuma.domain.nation.entity.Nation;
import jakarta.persistence.Column;

import java.time.LocalDate;

public record ClientRegistDTO(
        String email, String pw, String name, LocalDate birth,
        String phone, String address, String detailAddress,
        String passportNumber, String foreignRegistNumber, String nation) {

    public Client toClient(String encodePw, Nation clientNation) {
        return Client.builder()
                .email(email())
                .pw(encodePw)
                .name(name())
                .birth(birth())
                .phone(phone())
                .address(address())
                .detailAddress(detailAddress())
                .nation(clientNation)
                .build();
    }
}
