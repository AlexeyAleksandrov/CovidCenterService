package ru.alexeyaleksandrov.covidcenterservice.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/secured")
public class MainController
{
    @GetMapping("/user")
    public String userAccess(Principal principal)
    {
        if(principal == null)
        {
            return null;
        }
        return principal.getName();
    }
}
