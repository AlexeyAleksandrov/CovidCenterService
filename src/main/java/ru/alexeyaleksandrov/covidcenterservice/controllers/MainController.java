package ru.alexeyaleksandrov.covidcenterservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alexeyaleksandrov.covidcenterservice.models.users.LoginHistory;
import ru.alexeyaleksandrov.covidcenterservice.repositories.users.LoginHistoryRepository;

import java.security.Principal;

@RestController
@RequestMapping("/secured")
@AllArgsConstructor
public class MainController
{
    @GetMapping("/user")
//    @Secured("ROLE_ASSISTANT_RESEARCHER")
//    @PreAuthorize("hasAnyAuthority('ROLE_ASSISTANT_RESEARCHER')")
    public String userAccess(Principal principal)
    {
        if(principal == null)
        {
            return null;
        }
        return principal.getName();
    }

}
