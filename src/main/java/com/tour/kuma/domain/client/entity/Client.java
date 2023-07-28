package com.tour.kuma.domain.client.entity;

import com.tour.kuma.domain.nation.entity.Nation;
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
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column
    private char foreignYn;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="clientId")
    private ClientForeign clientForeign;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nationId")
    private Nation nation;

    public void addClientForeign(ClientForeign clientForeign) {
        this.clientForeign = clientForeign;
    }
}
