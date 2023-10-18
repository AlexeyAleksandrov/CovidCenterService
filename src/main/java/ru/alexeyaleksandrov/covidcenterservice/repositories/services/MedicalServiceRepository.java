package ru.alexeyaleksandrov.covidcenterservice.repositories.services;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexeyaleksandrov.covidcenterservice.models.services.MedicalService;

public interface MedicalServiceRepository extends JpaRepository<MedicalService, Long>
{}