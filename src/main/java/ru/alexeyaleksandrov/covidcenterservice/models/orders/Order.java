package ru.alexeyaleksandrov.covidcenterservice.models.orders;

import jakarta.persistence.*;
import lombok.Data;
import ru.alexeyaleksandrov.covidcenterservice.models.users.Patient;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "create_date", nullable = false)
    private LocalDate createDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status;

    @Column(name = "completion_date")
    private LocalDate completionDate;

    @OneToMany
    @JoinColumn(name = "order_id")
    private List<OrderService> services;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
}