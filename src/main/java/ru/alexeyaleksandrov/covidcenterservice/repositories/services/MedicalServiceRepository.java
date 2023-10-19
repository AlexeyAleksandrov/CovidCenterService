package ru.alexeyaleksandrov.covidcenterservice.repositories.services;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexeyaleksandrov.covidcenterservice.models.services.MedicalService;

import java.util.Optional;

public interface MedicalServiceRepository extends JpaRepository<MedicalService, Long>
{}