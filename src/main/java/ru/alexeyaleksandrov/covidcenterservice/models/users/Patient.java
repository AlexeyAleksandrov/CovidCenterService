package ru.alexeyaleksandrov.covidcenterservice.models.users;

import jakarta.persistence.*;
import ru.alexeyaleksandrov.covidcenterservice.models.insurance.InsurancePolicyCompany;

@Entity
@Table(name = "patients")
public class Patient
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "full_name", nullable = false, length = 50)
    private String fullName;

    @Column(name = "patronymic", nullable = false, length = 50)
    private String patronymic;

    @Column(name = "passport", nullable = false, length = 10)
    private String passport;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "insurance_policy", nullable = false)
    private InsurancePolicyCompany insurancePolicy;

    @Column(name = "login", length = 20)
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "birthday")
    private Long birthday;

    public Long getBirthday() {return birthday;}

    public void setBirthday(Long birthday) {this.birthday = birthday;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public String getLogin() {return login;}

    public void setLogin(String login) {this.login = login;}

    public InsurancePolicyCompany getInsurancePolicy() {return insurancePolicy;}

    public void setInsurancePolicy(InsurancePolicyCompany insurancePolicy) {this.insurancePolicy = insurancePolicy;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getPhoneNumber() {return phoneNumber;}

    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    public String getPassport() {return passport;}

    public void setPassport(String passport) {this.passport = passport;}

    public String getPatronymic() {return patronymic;}

    public void setPatronymic(String patronymic) {this.patronymic = patronymic;}

    public String getFullName() {return fullName;}

    public void setFullName(String fullName) {this.fullName = fullName;}

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}
}