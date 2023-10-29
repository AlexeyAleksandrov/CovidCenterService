package ru.alexeyaleksandrov.covidcenterservice.models.services;

import jakarta.persistence.*;
import lombok.Data;
import ru.alexeyaleksandrov.covidcenterservice.models.users.Member;

@Entity
@Table(name = "analyzer_results")
@Data
public class AnalyzerResult
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "analyzer_id", nullable = false)
    private Analyzer analyzer;

    @Column(name = "result", nullable = false)
    private Double result;

    @Column(name = "execution_time")
    private Long executionTime;

    @Column(name = "competition_time")
    private Long competitionTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    private AnalyzerResultStatus status;

    @Column(name = "accepted")
    private Boolean accepted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    private MedicalService service;
}