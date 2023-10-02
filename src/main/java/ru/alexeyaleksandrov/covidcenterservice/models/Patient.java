package ru.alexeyaleksandrov.covidcenterservice.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "patients")
@Data
public class Patient
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "patronymic", nullable = false, length = 50)
    private String patronymic;

    @Column(name = "passport", nullable = false, length = 10)
    private String passport;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "insurance_policy", nullable = false)
    private InsurancePolicy insurancePolicy;

    @Column(name = "login", length = 20)
    private String login;

    @Column(name = "password")
    private String password;
}