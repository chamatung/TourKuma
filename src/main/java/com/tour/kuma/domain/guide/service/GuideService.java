package com.tour.kuma.domain.guide.service;

import com.tour.kuma.domain.guide.dto.GuideLoginDTO;
import com.tour.kuma.domain.guide.dto.GuideRegistDTO;
import com.tour.kuma.domain.guide.entity.Guide;
import com.tour.kuma.domain.guide.repository.GuideQulificationRepository;
import com.tour.kuma.domain.guide.repository.GuideRepository;
import com.tour.kuma.domain.nation.repository.NationRepository;
import com.tour.kuma.global.common.error.ApiException;
import com.tour.kuma.global.common.error.ErrorMessage;
import com.tour.kuma.global.config.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GuideService {

    private final NationRepository nationRepository;
    private final GuideRepository guideRepository;
    private final GuideQulificationRepository guideQulificationRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public ResponseEntity regist(GuideRegistDTO regist) {
        Optional<Guide> guildeCheck = guideRepository.findByEmail(regist.email());
        registValidation(guildeCheck);

        Guide guide = guideRepository.save(regist.toGuide(bCryptPasswordEncoder.encode(regist.pw()), nationRepository.findByKorName(regist.nation())));
        guideQulificationRepository.save(regist.toGuideQualification(guide));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void registValidation(Optional<Guide> guildeCheck) {
        if(guildeCheck.isPresent()) throw new ApiException(ErrorMessage.REGIST_ERROR);
    }

    public ResponseEntity login(GuideLoginDTO login) {
        Optional<Guide> guideCheck = guideRepository.findByEmail(login.email());
        loginValidation(guideCheck, login);

        Map<String, Object> body = new HashMap<>();
        String token = jwtTokenProvider.createToken(guideCheck.get().getGuideId(), guideCheck.get().getEmail(), "basic");
        body.put("token",token);

        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    private void loginValidation(Optional<Guide> guideCheck, GuideLoginDTO login) {
        if(guideCheck.isPresent()) {
            if(!bCryptPasswordEncoder.matches(guideCheck.get().getPw(), login.pw())) {
                throw new ApiException(ErrorMessage.LOGIN_ERROR);
            }
        } else {
            throw new ApiException(ErrorMessage.LOGIN_ERROR);
        }
    }
}
