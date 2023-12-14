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
public class EditOrderRequest
{
    List<Long> services;
    OrderStatus orderStatus;

    public static EditOrderRequest fromOrder(Order order)
    {
        EditOrderRequest orderRequest = new EditOrderRequest();
        orderRequest.setServices(order.getServices().stream().map(s -> s.getService().getId()).toList());
        orderRequest.setOrderStatus(order.getStatus());

        return orderRequest;
    }
}
