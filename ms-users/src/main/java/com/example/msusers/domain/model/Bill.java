package com.example.msusers.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Bill {

    private String idBill;
    private String customerBill; //userId
    private String productBill;
    private Double totalPrice;
}
