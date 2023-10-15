package ru.alexeyaleksandrov.covidcenterservice.models.users;

import jakarta.persistence.*;
import lombok.Data;
import ru.alexeyaleksandrov.covidcenterservice.models.services.MedicalService;

import java.util.List;

@Entity
@Table(name = "members")
@Data
public class Member
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "login", nullable = false, length = 50)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "ip", length = 15)
    private String ip;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role", nullable = false)
    private Role role;

    @Column(name = "last_enter_time")
    private Long lastEnterTime;

    @ManyToMany
    private List<MedicalService> allowServices;
}