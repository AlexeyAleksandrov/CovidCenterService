package ru.alexeyaleksandrov.covidcenterservice.repositories.users;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexeyaleksandrov.covidcenterservice.models.users.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>
{}