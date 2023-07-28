package com.tour.kuma.domain.client.controller;

import com.tour.kuma.domain.client.service.ClientForeignService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/client-foreign")
public class ClientForeignController {

    private final ClientForeignService clientForeignService;


}
