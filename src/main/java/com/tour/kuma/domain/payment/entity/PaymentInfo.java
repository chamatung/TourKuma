package com.tour.kuma.domain.payment.entity;

import com.tour.kuma.domain.tourreservationinfo.Entity.TourReservationInfo;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class PaymentInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long PaymentInfoId;
    @Column(length = 10)
    private String state;
    private Integer price;
    @Column(length = 10)
    private String currency;
    @Column(length = 50)
    private String paymentId;
    private LocalDate paymentDate;
    @Column(length = 10)
    private String paymentMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tourReservationInfoId")
    private TourReservationInfo tourReservationInfo;
}
