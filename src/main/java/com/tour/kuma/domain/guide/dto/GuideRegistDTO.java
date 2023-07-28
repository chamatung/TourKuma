package com.tour.kuma.domain.guide.dto;


import com.tour.kuma.domain.guide.entity.Guide;
import com.tour.kuma.domain.guide.entity.GuideQualification;
import com.tour.kuma.domain.nation.entity.Nation;

import java.time.LocalDate;

public record GuideRegistDTO(
    String email,
    String pw,
    String name,
    LocalDate birth,
    char gender,
    String phone,
    String address,
    String detailAddress,
    String availableLanguage,
    int expreience,
    String specialties,
    boolean crimeYn,
    String nation,

    /** 자격증 관련 */
    String qualificationName,
    LocalDate certificationDate,
    String certificationAuthority,
    String certificationNumber,
    LocalDate validDate

) {
    public Guide toGuide(String pw, Nation nation) {
        return Guide.builder()
                .email(email())
                .pw(pw)
                .name(name())
                .birth(birth())
                .gender(gender())
                .phone(phone())
                .address(address())
                .detailAddress(detailAddress())
                .availableLanguage(availableLanguage())
                .experience(expreience())
                .specialties(specialties())
                .crimeYn(crimeYn())
                .nation(nation)
                .build();
    }

    public GuideQualification toGuideQualification(Guide guide) {
        return GuideQualification.builder()
                .qualificationName(qualificationName())
                .certificationDate(certificationDate())
                .certificationAuthority(certificationAuthority())
                .certificationNumber(certificationNumber())
                .validDate(validDate())
                .guide(guide)
                .build();
    }
}
