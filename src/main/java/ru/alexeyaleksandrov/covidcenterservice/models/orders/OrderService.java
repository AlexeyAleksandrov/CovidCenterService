package ru.alexeyaleksandrov.covidcenterservice.models.orders;

import jakarta.persistence.*;
import lombok.Data;
import ru.alexeyaleksandrov.covidcenterservice.models.services.AnalyzerResult;
import ru.alexeyaleksandrov.covidcenterservice.models.services.MedicalService;

@Entity
@Table(name = "orders_services")
@Data
public class OrderService
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "order_id", nullable = false)
//    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "service_id", nullable = false)
    private MedicalService service;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "analyzer_result")
    private AnalyzerResult analyzerResult;
}