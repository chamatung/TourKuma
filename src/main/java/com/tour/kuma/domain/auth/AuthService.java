package com.tour.kuma.domain.auth;

import java.util.Map;

public interface AuthService {
    String getToken(String code);
    Map<String,Object> getUserInfo(String accessToken);
}
