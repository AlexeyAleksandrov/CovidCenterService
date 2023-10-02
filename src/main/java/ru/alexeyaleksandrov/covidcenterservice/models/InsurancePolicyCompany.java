package ru.alexeyaleksandrov.covidcenterservice.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "insurance_policy_companies")
@Data
public class InsurancePolicyCompany
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "individual_tax_number", nullable = false)
    private Long individualTaxNumber;

    @Column(name = "checking_account", nullable = false)
    private Long checkingAccount;

    @Column(name = "bank_identification_code", nullable = false)
    private Long bankIdentificationCode;
}