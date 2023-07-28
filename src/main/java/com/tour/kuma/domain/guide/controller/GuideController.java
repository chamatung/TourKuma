package com.tour.kuma.domain.guide.controller;

import com.tour.kuma.domain.guide.dto.GuideLoginDTO;
import com.tour.kuma.domain.guide.dto.GuideRegistDTO;
import com.tour.kuma.domain.guide.service.GuideService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/guide")
@RequiredArgsConstructor
public class GuideController {

    private final GuideService guideService;

    @PostMapping()
    public ResponseEntity regist(@RequestBody @Valid GuideRegistDTO regist) {
        return guideService.regist(regist);
    }
    @GetMapping
    public ResponseEntity login(GuideLoginDTO login) {
        return guideService.login(login);
    }

}
