package ru.alexeyaleksandrov.covidcenterservice.models.orders;

import jakarta.persistence.*;
import lombok.Data;
import ru.alexeyaleksandrov.covidcenterservice.models.offices.MedicalUtility;
import ru.alexeyaleksandrov.covidcenterservice.models.users.Member;
import ru.alexeyaleksandrov.covidcenterservice.models.analyzer.AnalyzerResult;

@Entity
@Table(name = "orders_services")
@Data
public class OrdersService
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "service_id", nullable = false)
    private MedicalUtility medicalUtility;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrdersServiceStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "analyzer_result")
    private AnalyzerResult analyzerResult;
}