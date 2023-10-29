package ru.alexeyaleksandrov.covidcenterservice.models.users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.alexeyaleksandrov.covidcenterservice.models.insurance.InsurancePolicyCompany;
import ru.alexeyaleksandrov.covidcenterservice.models.insurance.SocialType;

@Entity
@Table(name = "patients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "full_name", nullable = false, length = 50)
    private String fullName;

    @Column(name = "passport", nullable = false, length = 10)
    private String passport;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "email", length = 50)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "insurance_policy", nullable = false)
    private InsurancePolicyCompany insurancePolicy;

    @Column(name = "login", length = 20)
    private String login;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "birthday")
    private Long birthday;

    @Column(name = "social_sec_number")
    private Long socialSecNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_type", length = 30)
    private SocialType socialType;

    @Column(name = "country", length = 50)
    private String country;

    @Column(name = "ip_address", length = 15)
    private String ipAddress;

    @Column(name = "user_agent", columnDefinition = "TEXT")
    private String userAgent;
}