package com.tour.kuma.domain.social.controller;

import com.tour.kuma.domain.social.service.SocialGoogleServiceImpl;
import com.tour.kuma.domain.social.service.SocialKakaoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/social")
public class SocialController {

    private final SocialKakaoServiceImpl kakaoService;

    /**
     * 소셜로그인 - kakao
     * */
    @PostMapping
    public ResponseEntity<String> kakaoAuth(@RequestParam String code) throws IOException {
        //매개변수 code는 인가코드 -> 이 인가코드를 통해 카카오 서버에 토큰 요청해야함
        String accessToken = kakaoService.getToken(code);
        MultiValueMap<String, String> header = kakaoService.getUserInfo(accessToken);


        return new ResponseEntity<>(header, HttpStatus.OK);
    }
    @GetMapping
    public void test(){
        System.out.println("test");
    }

}
