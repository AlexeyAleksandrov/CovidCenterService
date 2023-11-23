package ru.alexeyaleksandrov.covidcenterservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alexeyaleksandrov.covidcenterservice.models.users.LoginHistory;
import ru.alexeyaleksandrov.covidcenterservice.repositories.users.LoginHistoryRepository;

@RestController
@AllArgsConstructor
@RequestMapping("/loginhistory")
public class LoginHistoryController
{
    LoginHistoryRepository loginHistoryRepository;

    @GetMapping("/getBy")
    public ResponseEntity<LoginHistory> getByName(Long id)
    {
        return ResponseEntity.of(loginHistoryRepository.findById(id));
    }
}
