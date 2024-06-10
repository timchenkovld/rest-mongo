package com.example.restmongo.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String country;
    private String postcode;
    private String city;
}
