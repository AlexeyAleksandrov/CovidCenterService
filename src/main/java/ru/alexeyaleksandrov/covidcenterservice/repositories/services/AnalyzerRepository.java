package ru.alexeyaleksandrov.covidcenterservice.repositories.services;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexeyaleksandrov.covidcenterservice.models.services.Analyzer;

public interface AnalyzerRepository extends JpaRepository<Analyzer, Long>
{}