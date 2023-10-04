package ru.alexeyaleksandrov.covidcenterservice.models.users;

import jakarta.persistence.*;
import lombok.Data;
import ru.alexeyaleksandrov.covidcenterservice.models.users.control.Role;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "members")
@Data
public class Member
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "login", nullable = false, length = 50)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "ip", length = 15)
    private String ip;

    @Column(name = "last_enter_date")
    private LocalDate lastEnterDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role", nullable = false)
    private Role role;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "last_enter_time")
    private LocalTime lastEnterTime;
}