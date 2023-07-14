package com.tour.kuma.domain.client.service;

import com.tour.kuma.domain.client.dto.ClientLoginDTO;
import com.tour.kuma.domain.client.dto.ClientRegistDTO;
import com.tour.kuma.domain.client.entity.Client;
import com.tour.kuma.domain.client.repository.ClientRepository;
import com.tour.kuma.domain.nation.entity.Nation;
import com.tour.kuma.domain.nation.repository.NationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ClientService {

    private final ClientRepository clientRepository;
    private final NationRepository nationRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public ResponseEntity regist(ClientRegistDTO newClient) {
        Optional<Client> clientCkeck = clientRepository.findByEmail(newClient.email());
        MultiValueMap<String,String> header = new LinkedMultiValueMap<>();

        if(registValidation(clientCkeck)) {
            Nation clientNation = nationRepository.findByKorName(newClient.nation());
            Client client = newClient.toClient(bCryptPasswordEncoder.encode(newClient.pw()), clientNation);
            clientRepository.save(client);
            header.set("msg","성공");
        } else {
            header.set("msg","실패");
        }

        return new ResponseEntity<>(header, HttpStatus.OK);
    }

    private boolean registValidation(Optional<Client> clientCkeck) {
        if(!clientCkeck.isPresent()) return true;

        return false;
    }
    //security 적용 시 이 로직은 쓸 일이 없음
    public ResponseEntity login(ClientLoginDTO login) {
        Optional<Client> loginCheck = clientRepository.findByEmail(login.email());
        MultiValueMap<String,String> header = new LinkedMultiValueMap<>();

        if(loginValidation(loginCheck, login)) {
            header.set("msg","성공");
        } else {
            header.set("msg","실패");
        }
        return new ResponseEntity<>(header, HttpStatus.OK);
    }

    private boolean loginValidation(Optional<Client> loginCheck, ClientLoginDTO login) {
        if(loginCheck.isPresent()) {
            if(bCryptPasswordEncoder.matches(loginCheck.get().getPw(), login.pw())) {
                return true;
            }
        }
        return false;
    }
}
