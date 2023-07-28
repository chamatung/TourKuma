package com.tour.kuma.domain.guide.entity;

import com.tour.kuma.domain.nation.entity.Nation;
import com.tour.kuma.global.util.BooleanToYNConverter;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
public class Guide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long guideId;
    @Column(length = 50)
    private String email;
    @Column(length = 255)
    private String pw;
    @Column(length = 30)
    private String name;
    private LocalDate birth;
    private char gender;
    @Column(length = 15)
    private String phone;
    @Column(length = 300)
    private String address;
    @Column(length = 300)
    private String detailAddress;
    @Column(length = 50)
    private String availableLanguage;
    private Integer experience;
    @Column(length = 255)
    private String specialties;

    @Convert(converter = BooleanToYNConverter.class)
    private Boolean crimeYn; //범죄여부 y,n

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nationId")
    private Nation nation;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "guide")
    private List<GuideQualification> guideQualification = new ArrayList<>();
}
