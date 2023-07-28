package com.tour.kuma.domain.client.dto;

import com.tour.kuma.domain.client.entity.Client;
import com.tour.kuma.domain.client.entity.ClientForeign;
import com.tour.kuma.domain.nation.entity.Nation;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record ClientRegistDTO(
        @NotBlank(message = "email이 공백입니다.") String email,
        @NotBlank(message = "비밀번호가 공백입니다.") String pw,
        @NotBlank(message = "이름이 공백입니다.") String name,
        LocalDate birth,
        @NotBlank(message = "연락처가 공백입니다.") String phone,
        @NotBlank(message = "주소가 공백입니다.") String address,
        String detailAddress,
        char foreignYn,
        @NotBlank String passportNumber,
        @NotBlank String foreignRegistNumber,
        @NotBlank String nation
) {

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
                .foreignYn(foreignYn())
                .build();
    }

    public ClientForeign toClientForeign() {
        return ClientForeign.builder()
                .passportNumber(passportNumber())
                .foreignRegistNumber(foreignRegistNumber())
                .build();
    }
}
