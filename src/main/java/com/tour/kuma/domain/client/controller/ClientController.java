package com.tour.kuma.domain.client.controller;

import com.tour.kuma.domain.auth.AuthKakaoServiceImpl;
import com.tour.kuma.domain.client.dto.ClientLoginDTO;
import com.tour.kuma.domain.client.dto.ClientRegistDTO;
import com.tour.kuma.domain.client.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

    private final ClientService clientService;

    private final AuthKakaoServiceImpl kakaoService;

    @PostMapping
    public ResponseEntity regist(ClientRegistDTO newClient) {
        return clientService.regist(newClient);
    }

    @GetMapping
    public ResponseEntity auth(ClientLoginDTO login) {
        return clientService.login(login);
    }

    //인가코드를 프론트측에서 받은 이후 프론트에서 인가코드를 전달 이유는 프론트와 서버가 나누어져 있기때문에
    // 만약 서버사이드랜더링이면 인가코드 리턴받을 주소를 여기로 하면 된다.
    @GetMapping("/auth")
    public String kakaoAuth(String code) throws IOException {
        //매개변수 code는 인가코드 -> 이 인가코드를 통해 카카오 서버에 토큰 요청해야함
        String accessToken = kakaoService.getToken(code);
        Map<String,Object> userInfo = kakaoService.getUserInfo(accessToken);

        return accessToken;
    }
}
