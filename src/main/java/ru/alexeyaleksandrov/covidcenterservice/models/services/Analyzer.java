package ru.alexeyaleksandrov.covidcenterservice.models.services;

//import jakarta.persistence.*;
import lombok.Data;

import javax.persistence.*;

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