package com.tour.kuma.domain.client.repository;


import com.tour.kuma.domain.client.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long>, ClientCustomRepository {
    Optional<Client> findByEmail(String email);
}
