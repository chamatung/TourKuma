package com.tour.kuma.domain.social.entity;

import com.tour.kuma.domain.client.entity.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Social {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long socialId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientId")
    private Client client;

    @Column(length = 30)
    private String email;

    @Column(length = 10)
    private String socialType;
}
