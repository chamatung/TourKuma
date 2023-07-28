package com.tour.kuma.domain.client.repository;

import com.tour.kuma.domain.client.entity.ClientForeign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientForeignRepository extends JpaRepository<ClientForeign, Long> {
}
