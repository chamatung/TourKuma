package com.tour.kuma.domain.social.service;

import com.tour.kuma.domain.client.entity.Client;
import com.tour.kuma.domain.client.repository.ClientRepository;
import com.tour.kuma.domain.social.dto.SocialRegistDTO;
import com.tour.kuma.domain.social.entity.Social;
import com.tour.kuma.domain.social.repository.SocialRepository;
import com.tour.kuma.global.config.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SocialKakaoServiceImpl implements SocialService {

    private final SocialRepository socialRepository;

    private final JwtTokenProvider jwtTokenProvider;


    @Override
    public String getToken(String code) {
        // Kakao OAuth 토큰 엔드포인트 URL
        String tokenEndpoint = "https://kauth.kakao.com/oauth/token";

        // RestTemplate 객체 생성
        RestTemplate restTemplate = new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofMillis(5000))//연결타임아웃
                .setReadTimeout(Duration.ofMillis(5000))//읽기타임아웃
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // Request Body 파라미터
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", "authorization_code");
        requestBody.add("client_id", "7179ae74cbd07a526a748fa81d0dc99f");
        requestBody.add("redirect_uri", "http://localhost:3000/");
        requestBody.add("code", code);

        // Request 생성
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        //	<T> ResponseEntity<T> exchange(String url, HttpMethod method, @Nullable HttpEntity<?> requestEntity,
        //	Class<T> responseType, Object... uriVariables) throws RestClientException;
        ResponseEntity<Map> response = restTemplate.exchange(tokenEndpoint, HttpMethod.POST, requestEntity, Map.class);
        // 응답 결과 확인
        HttpStatusCode statusCode = response.getStatusCode();
        HttpHeaders responseHeaders = response.getHeaders();
        Map<String, Object> responseBody = response.getBody();
        String accessToken = "";
        String refreshToken = "";
        if (statusCode == HttpStatus.OK) {
            accessToken = (String) responseBody.get("access_token");
            refreshToken = (String) responseBody.get("refresh_token");



        } else {
            // 에러 처리
            // ...
        }

        return accessToken;
    }

    @Transactional
    @Override
    public MultiValueMap<String, String> getUserInfo(String accessToken) {
        //    요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
        String reqURL = "https://kapi.kakao.com/v2/user/me";

        RestTemplate restTemplate = new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofMillis(5000))//연결타임아웃
                .setReadTimeout(Duration.ofMillis(5000))//읽기타임아웃
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer " + accessToken );

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(reqURL, HttpMethod.GET, requestEntity, Map.class);
        // 응답 결과 확인
        HttpStatusCode statusCode = response.getStatusCode();
//        HttpHeaders responseHeaders = response.getHeaders();
        Map<String, Object> responseBody = response.getBody();
        Map<String, Object> kakao_account = (Map<String, Object>) responseBody.get("kakao_account");
        Long id = -1L;
        String email = "";

        MultiValueMap<String,String> header = new LinkedMultiValueMap<>();
        if (statusCode == HttpStatus.OK) {
            id = (Long) responseBody.get("id");
            email = (String) kakao_account.get("email");
            String token = jwtTokenProvider.createToken(id,email,"kakao");
            if(!socialValidation(id)) {
                socialRepository.save(SocialRegistDTO.toSocial(id,email,"kakao"));
                header.add("token", token);
                header.add("client_status", "소셜회원가입"); // 임시권한이 부여시 프론트단 체크 후 회원가입 페이지로 이동이 필요 해당 토큰은 회원가입 시 다시 전달해줘야함.
            } else {
                if(foreignValidation(id)) {
                    header.add("client_status", "외국인등록");
                }
            }
        } else {
            throw new RuntimeException("Kakao Login fail");
        }

        return header;
    }

    private boolean foreignValidation(Long id) {
        Social social = socialRepository.findBySocialId(id);
        Client client = social.getClient();
        if(client.getForeignYn() == 'y') {
            if(client.getClientForeign() == null) {
                return false;
            }
        }
        return true;
    }

    private boolean socialValidation(Long id) {
        Social social = socialRepository.findBySocialId(id);

        return social != null;
    }
}
