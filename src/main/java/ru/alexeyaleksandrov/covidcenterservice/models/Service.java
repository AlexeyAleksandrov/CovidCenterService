package ru.alexeyaleksandrov.covidcenterservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "services")
@Data
public class Service
{
    @Id
    @Column(name = "code", nullable = false)
    private Long id;

    @Column(name = "service_name", nullable = false)
    private String serviceName;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "deadline")
    private Integer deadline;

    @Column(name = "avg_deviation")
    private Double avgDeviation;
}