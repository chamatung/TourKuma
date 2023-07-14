package com.tour.kuma.domain.auth;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Map;

public class AuthKakaoServiceImpl implements AuthService{
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
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);


        // Request Body 파라미터
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", "authorization_code");
        requestBody.add("client_id", "나의 개발자 key");
        requestBody.add("redirect_uri", "localhost:8080/api/v1/client/auth");//인가코드가 redirect된 uri인데 react이면 front인가 ?
        requestBody.add("code", code);

        // Request 생성
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        //	<T> ResponseEntity<T> exchange(String url, HttpMethod method, @Nullable HttpEntity<?> requestEntity,
        //			Class<T> responseType, Object... uriVariables) throws RestClientException;
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

            // 토큰 처리 또는 추가 작업 수행
            // ...
        } else {
            // 에러 처리
            // ...
        }

        return accessToken;
    }

    @Override
    public Map<String, Object> getUserInfo(String accessToken) {
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
        HttpHeaders responseHeaders = response.getHeaders();
        Map<String, Object> responseBody = response.getBody();
        String test1 = "";
        String test = "";
        if (statusCode == HttpStatus.OK) {
            test = (String) responseBody.get("test1");
            test1 = (String) responseBody.get("test2");

            // 유저정보로 보낼지 말지 작업할지 말지
        } else {
            // 에러 처리
            // ...
        }

        return responseBody;
    }
}
