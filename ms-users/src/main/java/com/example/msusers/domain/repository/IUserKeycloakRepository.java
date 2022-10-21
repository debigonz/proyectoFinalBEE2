package com.example.msusers.domain.repository;

import com.example.msusers.domain.model.UserKeycloak;

public interface IUserKeycloakRepository {

    UserKeycloak findById(String id);
}
