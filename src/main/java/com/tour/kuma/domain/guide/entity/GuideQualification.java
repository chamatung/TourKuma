package com.tour.kuma.domain.guide.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuideQualification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long guideQualificationId;
    @Column(length = 50)
    private String qualificationName;
    private LocalDate certificationDate;
    @Column(length = 50)
    private String certificationAuthority;
    @Column(length = 50)
    private String certificationNumber;
    private LocalDate validDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guideId")
    private Guide guide;

}
