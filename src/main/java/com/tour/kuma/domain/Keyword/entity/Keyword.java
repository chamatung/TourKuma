package com.tour.kuma.domain.Keyword.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.valueextraction.UnwrapByDefault;
import lombok.Getter;

@Entity
@Getter
public class Keyword {

    @Id
    private Long keywordId;
    @Column(length = 255)
    private String keyword;

}
