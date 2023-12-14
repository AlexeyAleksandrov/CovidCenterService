package ru.alexeyaleksandrov.covidcenterservice.controllers.orders;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alexeyaleksandrov.covidcenterservice.dto.orders.CreateOrderRequest;
import ru.alexeyaleksandrov.covidcenterservice.dto.orders.EditOrderRequest;
import ru.alexeyaleksandrov.covidcenterservice.dto.orders.OrderRequest;
import ru.alexeyaleksandrov.covidcenterservice.models.orders.Order;
import ru.alexeyaleksandrov.covidcenterservice.models.orders.OrderService;
import ru.alexeyaleksandrov.covidcenterservice.models.orders.OrderStatus;
import ru.alexeyaleksandrov.covidcenterservice.models.services.AnalyzerResult;
import ru.alexeyaleksandrov.covidcenterservice.models.services.AnalyzerResultStatus;
import ru.alexeyaleksandrov.covidcenterservice.models.services.MedicalService;
import ru.alexeyaleksandrov.covidcenterservice.models.users.User;
import ru.alexeyaleksandrov.covidcenterservice.repositories.orders.OrderRepository;
import ru.alexeyaleksandrov.covidcenterservice.repositories.orders.OrderServiceRepository;
import ru.alexeyaleksandrov.covidcenterservice.repositories.services.AnalyzerResultRepository;
import ru.alexeyaleksandrov.covidcenterservice.repositories.services.MedicalServiceRepository;
import ru.alexeyaleksandrov.covidcenterservice.repositories.users.UsersRepository;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrdersController
{
    OrderRepository orderRepository;    // репозиторий заказов
    UsersRepository usersRepository;
    MedicalServiceRepository medicalServiceRepository;
    OrderServiceRepository orderServiceRepository;
    AnalyzerResultRepository analyzerResultRepository;

//    @GetMapping("/{id}")
////    @PreAuthorize("hasRole('ASSISTANT_RESEARCHER')") // требуется авторизация пользователя
////    @PreAuthorize("hasAnyAuthority('ROLE_ASSISTANT_RESEARCHER')")
//    public ResponseEntity<Order> getById(@PathVariable Long id)
//    {
//        try
//        {
//            Order order = orderRepository.findById(id).orElseThrow();   // ищем заказ
//            return ResponseEntity.ok(order);    // если он найден, возвращаем его
//        }
//        catch (NoSuchElementException elementException)     // если заказ не найден
//        {
//            return ResponseEntity.notFound().build();   // сообщаем ошибку 404
//        }
//    }

    @GetMapping("/")
    public ResponseEntity<List<OrderRequest>> getAllOrders()
    {
        List<OrderRequest> orders = orderRepository.findAll().stream().map(OrderRequest::fromOrder).toList();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderRequest>> getUserOrders(@PathVariable Long userId)
    {
        List<OrderRequest> userOrders = orderRepository.findOrderByUserId(userId).stream().map(OrderRequest::fromOrder).toList();
        return new ResponseEntity<>(userOrders, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable Long orderId)
    {
        try
        {
            Order order = orderRepository.findById(orderId).orElseThrow();
            return new ResponseEntity<>(order, HttpStatus.OK);
        }
        catch (NoSuchElementException e)
        {
            return new ResponseEntity<>("Заказ не найден!", HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/")
    public ResponseEntity<?> addOrder(@RequestBody CreateOrderRequest request, Principal principal)
    {
        // проверка на авторизацию
        if (principal == null)
        {
            return ResponseEntity.badRequest().body("Необходима авторизация!");
        }
        // проверка на пользователя
        User user = null;
        try
        {
            user = usersRepository.findUserByLogin(principal.getName()).orElseThrow();
        }
        catch (NoSuchElementException e)
        {
            return ResponseEntity.badRequest().body("Пользователь не найден!");
        }

        // проверка списка сервисов
        if (request.getServices().isEmpty())
        {
            return ResponseEntity.badRequest().body("Список услуг пуст!");
        }
        else
        {
            if (request.getServices().stream().anyMatch(s -> !medicalServiceRepository.existsById(s)))  // если есть хотя бы 1 сервис, отсутствующий в БД
            {
                return ResponseEntity.badRequest().body("Некорректный список услуг!");
            }
        }

        // создание заказа
        Order order = new Order();
        order.setCreateDate(LocalDate.now());   // дата создания заказа
        order.setCompletionDate(null);   // ориентировочная дата завершения заказа
        order.setStatus(OrderStatus.CREATED);   // заказ создан
        order.setUser(user);    // пользователь, создавший заказ
        User finalUser = user;
        order.setServices(request.getServices().stream().map(s -> {
            OrderService orderService = new OrderService();
            MedicalService medicalService = medicalServiceRepository.findById(s).orElseThrow();
            orderService.setService(medicalService);

            AnalyzerResult analyzerResult = new AnalyzerResult();
            analyzerResult.setService(medicalService);
            analyzerResult.setUser(finalUser);
            analyzerResult.setStatus(AnalyzerResultStatus.NOT_STARTED);

            analyzerResult = analyzerResultRepository.save(analyzerResult);
            orderService.setAnalyzerResult(analyzerResult);

            orderService = orderServiceRepository.save(orderService);

            return orderService;
        }).toList());    // список услуг, из ID получаем список услуг

        Order newOrder = orderRepository.save(order);
        return new ResponseEntity<>(OrderRequest.fromOrder(newOrder), HttpStatus.CREATED);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<?> updateOrder(@PathVariable Long orderId, @RequestBody EditOrderRequest editOrderRequest, Principal principal)
    {
        // проверка на авторизацию
        if (principal == null)
        {
            return ResponseEntity.badRequest().body("Необходима авторизация!");
        }
        // проверка на пользователя
        User user = null;
        try
        {
            user = usersRepository.findUserByLogin(principal.getName()).orElseThrow();
        }
        catch (NoSuchElementException e)
        {
            return ResponseEntity.badRequest().body("Пользователь не найден!");
        }

        // проверка списка сервисов
        if (editOrderRequest.getServices().isEmpty())
        {
            return ResponseEntity.badRequest().body("Список услуг пуст!");
        }
        else
        {
            if (editOrderRequest.getServices().stream()
                    .anyMatch(s -> !medicalServiceRepository.existsById(s)))  // если есть хотя бы 1 сервис, отсутствующий в БД
            {
                return ResponseEntity.badRequest().body("Некорректный список услуг!");
            }
        }

        // обновление данных заказа

        Order order = null;
        try
        {
            order = orderRepository.findById(orderId).orElseThrow();
        }
        catch (NoSuchElementException e)
        {
            return ResponseEntity.badRequest().body("Заказ не найден!");
        }

        List<OrderService> services = new ArrayList<>();      // список сервисов

        for (OrderService orderService : order.getServices())  // удаляем услуги, которые теперь не нужны
        {
            if(editOrderRequest.getServices().stream()
                    .noneMatch(s -> orderService.getService().getId().equals(s)))     // если в новом списке отсутствует услуга, то удаляем её
            {
                orderServiceRepository.deleteById(orderService.getId());    // удаляем
                analyzerResultRepository.deleteById(orderService.getAnalyzerResult().getId());
            }
            else
            {
                services.add(orderService);     // иначе добавляем его в список
            }
        }

        User finalUser = user;  // костыль
        List<OrderService> addedOrderServices = editOrderRequest.getServices().stream()
                .filter(s -> services.stream()
                        .noneMatch(orderService -> orderService.getService().getId().equals(s)))    // оставляем те, которых ещё нет
                .map(s -> {
                    OrderService orderService = new OrderService();
                    MedicalService medicalService = medicalServiceRepository.findById(s).orElseThrow();
                    orderService.setService(medicalService);

                    AnalyzerResult analyzerResult = new AnalyzerResult();
                    analyzerResult.setService(medicalService);
                    analyzerResult.setUser(finalUser);
                    analyzerResult.setStatus(AnalyzerResultStatus.NOT_STARTED);

                    analyzerResult = analyzerResultRepository.save(analyzerResult);
                    orderService.setAnalyzerResult(analyzerResult);

                    orderService = orderServiceRepository.save(orderService);

                    return orderService;
                })  // добавляем новые
                .toList();

        services.addAll(addedOrderServices);    // добавляем новые услуги в список

        order.setServices(services);    // обновляем список услуг
        order.setStatus(editOrderRequest.getOrderStatus());     // обновляем статус

        Order updatedOrder = orderRepository.save(order);
        return new ResponseEntity<>(OrderRequest.fromOrder(updatedOrder), HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderId)
    {
        try
        {
            Order deletedOrder = orderRepository.findById(orderId).orElseThrow();
            orderRepository.deleteById(orderId);
            return new ResponseEntity<>(OrderRequest.fromOrder(deletedOrder), HttpStatus.NO_CONTENT);
        }
        catch (NoSuchElementException e)
        {
            return new ResponseEntity<>("Заказ не найден!", HttpStatus.NOT_FOUND);
        }
    }
}
