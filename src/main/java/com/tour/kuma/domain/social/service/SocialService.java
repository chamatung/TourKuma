package com.tour.kuma.domain.social.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface SocialService {
    String getToken(String code);
    ResponseEntity<Map> getUserInfo(String accessToken);
}
