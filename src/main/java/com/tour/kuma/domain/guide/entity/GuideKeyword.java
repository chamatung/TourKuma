package com.tour.kuma.domain.guide.entity;

import com.tour.kuma.domain.Keyword.entity.Keyword;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class GuideKeyword {

    @Id
    private Long guideKeywordId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keywordId")
    private Keyword keyword;
}
