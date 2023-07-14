package com.tour.kuma.domain.client.entity;

import com.tour.kuma.domain.nation.entity.Nation;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class ClientPaymentInfo {

    @Id
    private Long clientId; // 동일한 식별자를 가지도록 수정

    @MapsId // 매핑할 식별자
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientId") // 외래 키 컬럼명
    private Client client;
    
    @Column(length = 16)
    private String cardNumber;
    @Column(length = 30)
    private String cardholderName;
    private LocalDate expirationDate;
    @Column(length = 4)
    private String cvv;

    @OneToOne
    @JoinColumn(name = "nationId")
    private Nation nation;

}
