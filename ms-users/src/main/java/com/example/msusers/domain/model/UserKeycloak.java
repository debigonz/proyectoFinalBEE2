package com.example.msusers.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserKeycloak {

    private String id;
    private String username;
    private String email;
    private String firstname;
}
