package ru.alexeyaleksandrov.covidcenterservice.repositories.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexeyaleksandrov.covidcenterservice.models.orders.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>
{
    List<Order> findOrderByUserId(Long userId);
//    void deleteById(Long orderId);
}