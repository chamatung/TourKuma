package com.tour.kuma.domain.tourreservationinfo.Entity;

import com.tour.kuma.domain.client.entity.Client;
import com.tour.kuma.domain.guide.entity.Guide;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class TourReservationInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tourReservationInfoId;

    private LocalDate tourReservationInfoDate;
    private Integer price;
    private LocalDate startDate;
    private LocalDate endDate;
    @Column(length = 10)
    private String state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientId")
    private Client tourClient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guideId")
    private Guide guide;

}
