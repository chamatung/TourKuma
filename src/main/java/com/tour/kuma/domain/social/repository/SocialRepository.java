package com.tour.kuma.domain.social.repository;

import com.tour.kuma.domain.social.entity.Social;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialRepository extends JpaRepository<Social, Long>, SocialCustomRepository {
    Social findBySocialId(Long id);
}
