package ru.alexeyaleksandrov.covidcenterservice.models.insurancepolicy;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "insurance_policies")
@Data
public class InsurancePolicy
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "number")
    private Long number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company")
    private InsurancePolicyCompany company;

    @Lob
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private InsurancePolicyType type;
}