package com.tour.kuma.domain.guide.entity;

import com.tour.kuma.domain.common.entity.File;
import com.tour.kuma.domain.tourreservationinfo.Entity.TourGuideReview;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class GuideReviewPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long guideReviewPhotoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tourGuideReviewId;")
    private TourGuideReview tourGuideReview;

    @OneToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "fileId")
    private File file;

}
