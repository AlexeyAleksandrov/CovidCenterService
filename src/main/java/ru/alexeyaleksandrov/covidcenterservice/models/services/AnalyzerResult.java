package ru.alexeyaleksandrov.covidcenterservice.models.services;

//import jakarta.persistence.*;
import lombok.Data;
import ru.alexeyaleksandrov.covidcenterservice.models.users.User;
import javax.persistence.*;

@Entity
@Table(name = "analyzer_results")
@Data
public class AnalyzerResult
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "analyzer_id")
    private Analyzer analyzer;

    @Column(name = "result")
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_id")
    private MedicalService service;
}