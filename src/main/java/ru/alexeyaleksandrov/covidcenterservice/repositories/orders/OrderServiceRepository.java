package ru.alexeyaleksandrov.covidcenterservice.repositories.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexeyaleksandrov.covidcenterservice.models.orders.OrderService;

public interface OrderServiceRepository extends JpaRepository<OrderService, Long>
{}