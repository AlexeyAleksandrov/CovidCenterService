package ru.alexeyaleksandrov.covidcenterservice.models.users;

//import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.alexeyaleksandrov.covidcenterservice.models.insurance.InsurancePolicyCompany;
import ru.alexeyaleksandrov.covidcenterservice.models.insurance.SocialType;
import ru.alexeyaleksandrov.covidcenterservice.models.services.MedicalService;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User
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

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "role", nullable = false)
    private Role role;

    @Column(name = "last_enter_time")
    private Long lastEnterTime;

    // для сотрудника
    @ManyToMany
    private List<MedicalService> allowServices;

    @Column(name = "email", length = 50)
    private String email;

    // для пациента
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "insurance_policy_id", nullable = false)
    private InsurancePolicyCompany insurancePolicy;

    @Column(name = "birthday")
    private Long birthday;

    @Column(name = "social_sec_number")
    private Long socialSecNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_type", length = 30)
    private SocialType socialType;

    @Column(name = "country", length = 50)
    private String country;

    @Column(name = "user_agent", columnDefinition = "TEXT")
    private String userAgent;
}