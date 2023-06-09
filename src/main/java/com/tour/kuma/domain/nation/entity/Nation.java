package com.tour.kuma.domain.nation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Nation {

    @Id
    private Long nationId;
    @Column(length = 100)
    private String engName;
    @Column(length = 100)
    private String korName;
    @Column(length = 2)
    private String twoNationCode;
    @Column(length = 3)
    private String threeNationCode;
    @Column(length = 30)
    private String continentEngCode;
    @Column(length = 30)
    private String continentKorStandardCode;
    @Column(length = 30)
    private String continentMinistryForeignAffairsCode;
    @Column(length = 10)
    private String numericCode;

}

