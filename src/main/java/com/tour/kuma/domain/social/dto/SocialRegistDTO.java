package com.tour.kuma.domain.social.dto;

import com.tour.kuma.domain.social.entity.Social;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SocialRegistDTO {

    private final Long id;
    private final String email;
    private final String socialType;

    public static Social toSocial(Long id, String email, String socialType) {
        return Social.builder()
                .socialId(id)
                .email(email)
                .socialType(socialType)
                .build();
    }

}
