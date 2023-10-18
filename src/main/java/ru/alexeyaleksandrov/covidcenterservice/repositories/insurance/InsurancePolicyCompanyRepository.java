package ru.alexeyaleksandrov.covidcenterservice.repositories.insurance;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexeyaleksandrov.covidcenterservice.models.insurance.InsurancePolicyCompany;

public interface InsurancePolicyCompanyRepository extends JpaRepository<InsurancePolicyCompany, Long>
{}