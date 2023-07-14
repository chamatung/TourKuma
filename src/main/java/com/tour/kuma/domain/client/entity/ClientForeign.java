package com.tour.kuma.domain.client.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientForeign {

    @Id
    private Long clientId; // 동일한 식별자를 가지도록 수정

    @MapsId // 매핑할 식별자
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientId") // 외래 키 컬럼명
    private Client client;

    @Column(length = 255)
    private String passportNumber;
    @Column(length = 255)
    private String foreignRegistNumber;
}
