package com.tour.kuma.domain.tourreservationinfo.Entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class TourGuideReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tourGuideReviewId;
    @Column(length = 300)
    private String tourGuideReviewContent;
    private LocalDate tourGuideReviewDate;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tourReservationInfoId")
    private TourReservationInfo tourReservationInfo;




}
