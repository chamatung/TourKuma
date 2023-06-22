package com.tour.kuma.domain.guide.entity;

import com.tour.kuma.domain.administrativedistrict.entity.AdministrativeDistrict;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class GuideAdministrativeDistrict {

    @Id
    private Long guideAdministrativeDistrictId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "administrativeDistrictId")
    private AdministrativeDistrict administrativeDistrict;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guideId")
    private Guide guide;
}
