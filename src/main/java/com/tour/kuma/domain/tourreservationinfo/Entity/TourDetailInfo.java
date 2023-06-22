package com.tour.kuma.domain.tourreservationinfo.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class TourDetailInfo {

    @Id
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
