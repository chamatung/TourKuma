package com.tour.kuma.global.config;

import com.tour.kuma.domain.client.entity.Client;
import com.tour.kuma.domain.client.repository.ClientRepository;
import com.tour.kuma.domain.client.service.ClientService;
import com.tour.kuma.domain.social.entity.Social;
import com.tour.kuma.domain.social.repository.SocialRepository;
import com.tour.kuma.domain.social.service.SocialService;
import com.tour.kuma.global.common.error.ApiException;
import com.tour.kuma.global.common.error.ErrorMessage;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private static final String SECRET_KEY = "mySecretKey";
    private final ClientRepository clientRepository;
    private final SocialRepository socialRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        // 특정 경로인 경우, 필터 로직을 뛰어넘고 다음 필터 또는 컨트롤러로 요청을 전달
        if ("/api/v1/client".equals(requestURI) || "/api/v1/client/regist".equals(requestURI) || "/api/v1/social/kakao".equals(requestURI) || "/api/v1/nation".equals(requestURI)) {
            filterChain.doFilter(request, response);
            return;
        }

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            return;
        }

        String token = authorizationHeader.substring(7);

        try {
            // JWT 토큰 검증
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();

            // 토큰 검증 로직 구현 (예: 사용자 정보를 가져와서 인증 처리)
            Long userId = Long.parseLong(claims.getSubject());
            String email = (String) claims.get("email");
            String socialType = (String) claims.get("socialType");

            if("basic".equalsIgnoreCase(socialType)) {
                Optional<Client> client = clientRepository.findById(userId);
                if(client.isPresent()) {
                    request.setAttribute("userId", userId); // 예시로 사용자 ID를 리퀘스트에 추가합니다.

                    // 다음 필터로 요청을 전달합니다.
                    filterChain.doFilter(request, response);
                } else {
                    response.setStatus(HttpStatus.SC_UNAUTHORIZED);
                }
            } else {
                Optional<Social> social = socialRepository.findById(userId);
                if(social.isPresent()) {
                    request.setAttribute("userId", userId); // 예시로 사용자 ID를 리퀘스트에 추가합니다.

                    // 다음 필터로 요청을 전달합니다.
                    filterChain.doFilter(request, response);
                } else {
                    response.setStatus(HttpStatus.SC_UNAUTHORIZED);
                }
            }
            // 사용자 정보를 가져와서 인증 처리하는 코드를 추가합니다.


        } catch (SignatureException | ExpiredJwtException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
        }
    }
}

