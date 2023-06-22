package com.tour.kuma.domain.administrativedistrict.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class AdministrativeDistrict {

    @Id
    private Long administrativeDistrictId;

    @Column(length = 10)
    private String administrativeDistrictCode;//행정구역코드
    @Column(length = 20)
    private String kor_area1;//시도
    @Column(length = 30)
    private String kor_area2;//시군구
    @Column(length = 50)
    private String kor_area3;//읍면동
    @Column(length = 20)
    private String eng_area1;
    @Column(length = 30)
    private String eng_area2;
    @Column(length = 50)
    private String eng_area3;

}
