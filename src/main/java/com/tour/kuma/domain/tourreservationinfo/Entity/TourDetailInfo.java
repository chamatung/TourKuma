package com.tour.kuma.domain.tourreservationinfo.Entity;


import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class TourDetailInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tourInfoId;

    @Column(length = 300)
    private String meetAddress;
    @Column(length = 300)
    private String meetAddressDetail;

    private LocalDate meetDate;
    @Column(length = 100)
    private String request;
    @Column(length = 100)
    private String notice;
}
