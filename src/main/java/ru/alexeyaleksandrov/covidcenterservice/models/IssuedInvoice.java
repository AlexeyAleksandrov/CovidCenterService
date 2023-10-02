package ru.alexeyaleksandrov.covidcenterservice.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "issued_invoices")
@Data
public class IssuedInvoice
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company", nullable = false)
    private InsurancePolicyCompany company;

    @Column(name = "value", nullable = false)
    private Long value;

    @Lob
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusType status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member", nullable = false)
    private Member member;
}