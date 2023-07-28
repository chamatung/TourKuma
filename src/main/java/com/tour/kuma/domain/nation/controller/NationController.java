package com.tour.kuma.domain.nation.controller;

import com.tour.kuma.domain.nation.dto.NationRegistDTO;
import com.tour.kuma.domain.nation.service.NationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/nation")
public class NationController {

    private final NationService nationService;

    @PostMapping
    public ResponseEntity nationListRegist(@RequestBody Map<String, List<NationRegistDTO>> nations) {

        nationService.nationListSaveAll(nations.get("nations"));

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
