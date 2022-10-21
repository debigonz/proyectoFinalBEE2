package com.example.msusers.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class User {

    private String id;
    private String username;
    private String email;
    private String firstname;
    private List<Bill> bills;
}
