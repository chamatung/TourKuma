package com.tour.kuma.domain.guide.repository;

import com.tour.kuma.domain.guide.entity.Guide;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GuideRepository extends JpaRepository<Guide, Long> {
    Optional<Guide> findByEmail(String email);
}
