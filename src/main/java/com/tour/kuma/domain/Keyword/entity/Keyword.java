package com.tour.kuma.domain.Keyword.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long keywordId;
    @Column(length = 255)
    private String keyword;

}
