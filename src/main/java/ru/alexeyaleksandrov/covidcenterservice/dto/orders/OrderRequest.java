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
    Long orderId;
    Long userId;
    String userFullName;
    List<Long> services;
    int orderStatus;

    public static OrderRequest fromOrder(Order order)
    {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setOrderId(order.getId());
        orderRequest.setUserId(order.getUser().getId());
        orderRequest.setUserFullName(order.getUser().getFullName());
        orderRequest.setServices(order.getServices().stream().map(s -> s.getService().getId()).toList());
        orderRequest.setOrderStatus(order.getStatus().ordinal());

        return orderRequest;
    }
}
