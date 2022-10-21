package com.example.msusers.service;

import com.example.msusers.domain.model.User;
import com.example.msusers.domain.model.UserKeycloak;
import com.example.msusers.domain.repository.IBillFeignRepository;
import com.example.msusers.domain.repository.IUserKeycloakRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private IUserKeycloakRepository userKeycloakRepository;
    private IBillFeignRepository billFeignRepository;

    public UserService(IUserKeycloakRepository userKeycloakRepository, IBillFeignRepository billFeignRepository) {
        this.userKeycloakRepository = userKeycloakRepository;
        this.billFeignRepository = billFeignRepository;
    }

    public User findUser(String userId) {
        UserKeycloak userK = userKeycloakRepository.findById(userId);
        if (userK !=  null) {
            return User.builder()
                    .id(userK.getId())
                    .username(userK.getUsername())
                    .firstname(userK.getFirstname())
                    .email(userK.getEmail())
                    .bills(billFeignRepository.getBills(userId))
                    .build();
        } else
            throw new RuntimeException();
    }


}
