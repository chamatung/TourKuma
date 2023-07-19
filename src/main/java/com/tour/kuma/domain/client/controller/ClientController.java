package com.tour.kuma.domain.client.controller;

import com.tour.kuma.domain.client.dto.ClientLoginDTO;
import com.tour.kuma.domain.client.dto.ClientRegistDTO;
import com.tour.kuma.domain.client.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity regist(ClientRegistDTO newClient) {
        return clientService.regist(newClient);
    }

    @GetMapping
    public ResponseEntity auth(ClientLoginDTO login) {
        return clientService.login(login);
    }

}
