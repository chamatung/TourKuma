package com.tour.kuma.domain.nation.service;

import com.tour.kuma.domain.nation.dto.NationRegistDTO;
import com.tour.kuma.domain.nation.entity.Nation;
import com.tour.kuma.domain.nation.repository.NationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NationService {

    private final NationRepository nationRepository;

    @Transactional
    public ResponseEntity nationListSaveAll(List<NationRegistDTO> nations) {
        List<Nation> nationList = new ArrayList<>();

        for(NationRegistDTO regist : nations) {
            nationList.add(regist.toNation());
        }
        nationRepository.saveAll(nationList);

        return new ResponseEntity(HttpStatus.OK);
    }

}
