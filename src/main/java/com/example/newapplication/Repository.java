package com.example.newapplication;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private Owner owner;
    private String name;
    private boolean fork;


}
