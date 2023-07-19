package com.tour.kuma.domain.social.controller;

import com.tour.kuma.domain.social.service.SocialGoogleServiceImpl;
import com.tour.kuma.domain.social.service.SocialKakaoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/social")
public class SocialController {

    private final SocialKakaoServiceImpl kakaoService;
    private final SocialGoogleServiceImpl googleService;

    //인가코드를 프론트측에서 받은 이후 프론트에서 인가코드를 전달 이유는 프론트와 서버가 나누어져 있기때문에
    // 만약 서버사이드랜더링이면 인가코드 리턴받을 주소를 여기로 하면 된다.
    @PostMapping
    public String kakaoAuth(@RequestParam String code) throws IOException {
        //매개변수 code는 인가코드 -> 이 인가코드를 통해 카카오 서버에 토큰 요청해야함
        String accessToken = kakaoService.getToken(code);
        ResponseEntity<Map> userInfo = kakaoService.getUserInfo(accessToken);

        return accessToken;
    }

    @PostMapping("/google")
    public String googleAuth(@RequestParam String code) {
        String accessToken = googleService.getToken(code);
//        ResponseEntity<Map> userInfo = googleService.getUserInfo(accessToken);


        return accessToken;
    }
}
