package com.tour.kuma.domain.social.service;

import com.tour.kuma.domain.client.entity.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.util.Map;
import java.util.Optional;

public interface SocialService {
    String getToken(String code);
    MultiValueMap<String,String> getUserInfo(String accessToken);

}
