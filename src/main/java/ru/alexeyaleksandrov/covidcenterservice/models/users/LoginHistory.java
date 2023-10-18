package ru.alexeyaleksandrov.covidcenterservice.models.users;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "login_history")
@Data
public class LoginHistory
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_login", nullable = false)
    private String login;

    @Column(name = "successful")
    private Boolean successful;

    @Column(name = "enter_date", nullable = false)
    private Long enterDate;
}