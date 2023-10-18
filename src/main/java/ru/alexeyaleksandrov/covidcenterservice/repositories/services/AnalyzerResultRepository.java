package ru.alexeyaleksandrov.covidcenterservice.repositories.services;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexeyaleksandrov.covidcenterservice.models.services.AnalyzerResult;

public interface AnalyzerResultRepository extends JpaRepository<AnalyzerResult, Long>
{}