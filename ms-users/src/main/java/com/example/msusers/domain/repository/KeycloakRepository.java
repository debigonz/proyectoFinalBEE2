package com.example.msusers.domain.repository;

import com.example.msusers.domain.model.UserKeycloak;
import org.keycloak.representations.idm.UserRepresentation;
import com.example.msusers.domain.model.User;
import org.keycloak.admin.client.Keycloak;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class KeycloakRepository implements IUserKeycloakRepository{

    @Autowired
    private Keycloak keycloak;

    @Value("${bills.keycloak.realm}")
    private String realm;
    @Override
    public UserKeycloak findById(String id) {
        UserRepresentation user = keycloak.realm(realm).users().get(id).toRepresentation();
        return toUser(user);
    }

    private UserKeycloak toUser(UserRepresentation userRepresentation){
        return new UserKeycloak(userRepresentation.getId(), userRepresentation.getUsername(), userRepresentation.getEmail(), userRepresentation.getFirstName());
    }
}
