package ru.alexeyaleksandrov.covidcenterservice.dto;

import lombok.Data;

@Data
public class SignUpRequest
{
    private String fullName;
    private String username;
    private String password;
}
