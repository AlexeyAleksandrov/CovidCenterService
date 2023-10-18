package ru.alexeyaleksandrov.covidcenterservice.repositories.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexeyaleksandrov.covidcenterservice.models.orders.Order;

public interface OrderRepository extends JpaRepository<Order, Long>
{}