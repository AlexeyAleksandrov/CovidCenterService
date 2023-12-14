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
public class OrderRequest
{
    Long userId;
    Long orderId;
    List<Long> services;
    OrderStatus orderStatus;

    public static OrderRequest fromOrder(Order order)
    {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setUserId(order.getUser().getId());
        orderRequest.setOrderId(order.getId());
        orderRequest.setServices(order.getServices().stream().map(s -> s.getService().getId()).toList());
        orderRequest.setOrderStatus(order.getStatus());

        return orderRequest;
    }
}
