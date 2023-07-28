package com.tour.kuma.domain.client.controller;

import com.tour.kuma.domain.client.dto.ClientLoginDTO;
import com.tour.kuma.domain.client.dto.ClientRegistDTO;
import com.tour.kuma.domain.client.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    /**
     * 일반 회원가입
     * */
    @PostMapping("/regist")
    public ResponseEntity regist(@RequestBody @Valid ClientRegistDTO newClient) {

        return clientService.regist(newClient);
    }

    /**
     * 소셜로그인 후 회원가입
     * */
    @PostMapping("{token}") // 소셜로그인 성공 후 회원가입 시
    public ResponseEntity regist(@PathVariable String token,@RequestBody ClientRegistDTO newClient) {
        return clientService.regist(token, newClient);
    }

    /**
     * 일반로그인
     * */
    @GetMapping
    public ResponseEntity auth(ClientLoginDTO login) {
        return clientService.login(login);
    }

}
