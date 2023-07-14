package com.tour.kuma.domain.nation.repository;


import com.tour.kuma.domain.nation.entity.Nation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NationRepository extends JpaRepository<Nation,Long> {
    Nation findByKorName(String nation);
}
