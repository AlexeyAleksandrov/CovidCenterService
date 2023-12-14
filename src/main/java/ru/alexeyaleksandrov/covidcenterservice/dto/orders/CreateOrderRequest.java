package ru.alexeyaleksandrov.covidcenterservice.dto.orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.alexeyaleksandrov.covidcenterservice.models.orders.Order;
import ru.alexeyaleksandrov.covidcenterservice.models.orders.OrderStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest
{
    private List<Long> services;
}
