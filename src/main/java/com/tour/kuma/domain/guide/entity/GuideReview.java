package com.tour.kuma.domain.guide.entity;

import com.tour.kuma.domain.client.entity.Client;
import com.tour.kuma.domain.nation.entity.Nation;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class GuideReview {

    @Id
    private Long guideReviewId;
    @Column(length = 300)
    private String guideReviewContent;
    private LocalDate guideReviewDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="guideId")
    private Guide guide;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="clientId")
    private Client tourClient;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nationId")
    private Nation nation;



}
