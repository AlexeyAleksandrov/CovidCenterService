package ru.alexeyaleksandrov.covidcenterservice.controllers.orders;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alexeyaleksandrov.covidcenterservice.models.orders.Order;
import ru.alexeyaleksandrov.covidcenterservice.repositories.orders.OrderRepository;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrdersController
{
    OrderRepository orderRepository;    // репозиторий заказов

    @GetMapping("/{id}")
//    @PreAuthorize("hasRole('USER')") // требуется авторизация пользователя
    public ResponseEntity<Order> getById(@PathVariable Long id)
    {
        try
        {
            Order order = orderRepository.findById(id).orElseThrow();   // ищем заказ
            return ResponseEntity.ok(order);    // если он найден, возвращаем его
        }
        catch (NoSuchElementException elementException)     // если заказ не найден
        {
            return ResponseEntity.notFound().build();   // сообщаем ошибку 404
        }
    }
}
