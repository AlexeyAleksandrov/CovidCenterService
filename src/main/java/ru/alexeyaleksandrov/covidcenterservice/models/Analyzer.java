package ru.alexeyaleksandrov.covidcenterservice.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "analyzers")
@Data
public class Analyzer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
}