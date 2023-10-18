package ru.alexeyaleksandrov.covidcenterservice.repositories.insurance;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexeyaleksandrov.covidcenterservice.models.insurance.IssuedInvoice;

public interface IssuedInvoiceRepository extends JpaRepository<IssuedInvoice, Long>
{}