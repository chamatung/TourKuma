package com.tour.kuma.domain.guide.entity;

import com.tour.kuma.domain.common.entity.File;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class GuideReviewPhoto {

    @Id
    private Long guideReviewPhotoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guideReviewId;")
    private GuideReview guideReview;

    @OneToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "fileId")
    private File file;

}
