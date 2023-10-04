package ru.alexeyaleksandrov.covidcenterservice.models.analyzer;

import jakarta.persistence.*;
import lombok.Data;

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
    @JoinColumn(name = "analyzer", nullable = false)
    private Analyzer analyzer;

    @Column(name = "result")
    private String result;

    @Column(name = "execution_time")
    private Long executionTime;

    @Column(name = "competition_time")
    private Long competitionTime;
}