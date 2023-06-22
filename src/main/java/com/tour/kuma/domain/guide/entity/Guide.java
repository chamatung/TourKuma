package com.tour.kuma.domain.guide.entity;

import com.tour.kuma.domain.nation.entity.Nation;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class Guide {

    @Id
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nationId")
    private Nation nation;

}
