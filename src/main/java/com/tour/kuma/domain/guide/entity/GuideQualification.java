package com.tour.kuma.domain.guide.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class GuideQualification {

    @Id
    private Long guideQualificationId;
    @Column(length = 50)
    private String qualificationName;
    private LocalDate certificationDate;
    @Column(length = 50)
    private String certificationAuthority;
    @Column(length = 50)
    private String certificationNumber;
    private LocalDate validDate;

}
