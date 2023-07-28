package com.tour.kuma.domain.client.service;

import com.tour.kuma.domain.client.dto.ClientLoginDTO;
import com.tour.kuma.domain.client.dto.ClientRegistDTO;
import com.tour.kuma.domain.client.entity.Client;
import com.tour.kuma.domain.client.entity.ClientForeign;
import com.tour.kuma.domain.client.repository.ClientForeignRepository;
import com.tour.kuma.domain.client.repository.ClientRepository;
import com.tour.kuma.domain.nation.entity.Nation;
import com.tour.kuma.domain.nation.repository.NationRepository;
import com.tour.kuma.domain.social.dto.SocialRegistDTO;
import com.tour.kuma.domain.social.entity.Social;
import com.tour.kuma.domain.social.repository.SocialRepository;
import com.tour.kuma.global.common.error.ApiException;
import com.tour.kuma.global.common.error.ErrorMessage;
import com.tour.kuma.global.config.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.LogManager;
import org.apache.catalina.Store;
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

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ClientService {

    private final ClientRepository clientRepository;
    private final NationRepository nationRepository;
    private final SocialRepository socialRepository;
    private final ClientForeignRepository clientForeignRepository;

    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public ResponseEntity regist(ClientRegistDTO newClient) {
        Optional<Client> clientCkeck = clientRepository.findByEmail(newClient.email());

        registValidation(clientCkeck);
        Client client = newClient.toClient(bCryptPasswordEncoder.encode(newClient.pw()), nationRepository.findByKorName(newClient.nation()));

        if(newClient.foreignYn() == 'Y') {
            ClientForeign clientForeign = newClient.toClientForeign();
            client.addClientForeign(clientForeign);
            clientForeign.addClient(client);

            clientRepository.save(client);
            clientForeignRepository.save(clientForeign);
        } else {
            clientRepository.save(client);
        }


        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity regist(String token, ClientRegistDTO newClient) {
        Optional<Client> clientCkeck = clientRepository.findByEmail(newClient.email());

        registValidation(clientCkeck);
        Client client = newClient.toClient(bCryptPasswordEncoder.encode(newClient.pw()), nationRepository.findByKorName(newClient.nation()));

        if(newClient.foreignYn() == 'Y') {
            ClientForeign clientForeign = newClient.toClientForeign();
            client.addClientForeign(clientForeign);
            clientForeign.addClient(client);

            clientRepository.save(client);
            clientForeignRepository.save(clientForeign);
        } else {
            clientRepository.save(client);
        }

        Social social = socialRepository.findBySocialId(jwtTokenProvider.extractUserId(token));
        social.changeClientId(client);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //security 적용 시 이 로직은 쓸 일이 없음
    public ResponseEntity login(ClientLoginDTO login) {
        Optional<Client> loginCheck = clientRepository.findByEmail(login.email());
        loginValidation(loginCheck, login);

        Map<String, Object> body = new HashMap<>();

        String token = jwtTokenProvider.createToken(loginCheck.get().getClientId(), loginCheck.get().getEmail(),"basic");
        body.put("token",token);
        if(foreignValidation(loginCheck.get())) {
            body.put("client_status", "외국인등록");
        }

        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    private void registValidation(Optional<Client> clientCkeck) {
        if(clientCkeck.isPresent()) throw new ApiException(ErrorMessage.REGIST_ERROR);
    }

    private void loginValidation(Optional<Client> loginCheck, ClientLoginDTO login) {
        if(loginCheck.isPresent()) {
            if(!bCryptPasswordEncoder.matches(loginCheck.get().getPw(), login.pw())) {
                throw new ApiException(ErrorMessage.LOGIN_ERROR);
            }
        } else {
            throw new ApiException(ErrorMessage.LOGIN_ERROR);
        }

    }

    private boolean foreignValidation(Client client) {
        if(client.getForeignYn() == 'y') {
            if(client.getClientForeign() == null) {
                return false;
            }
        }
        return true;
    }
}
