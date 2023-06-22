package com.tour.kuma.domain.client.entity;

import com.tour.kuma.domain.nation.entity.Nation;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class Client {

    @Id
    private Long clientId;
    @Column(length = 50)
    private String email;
    @Column(length = 255)
    private String pw;
    @Column(length = 30)
    private String name;
    private LocalDate birth;
    @Column(length = 15)
    private String phone;
    @Column(length = 300)
    private String address;
    @Column(length = 300)
    private String detailAddress;
    @Column(length = 255)
    private String passportNumber;
    @Column(length = 255)
    private String foreignRegistNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nationId")
    private Nation nation;
}
