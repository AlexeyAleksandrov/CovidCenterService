package ru.alexeyaleksandrov.covidcenterservice.models.insurance;

//import jakarta.persistence.*;
import lombok.Data;
import ru.alexeyaleksandrov.covidcenterservice.models.orders.OrderService;
import ru.alexeyaleksandrov.covidcenterservice.models.users.User;
import javax.persistence.*;

@Entity
@Table(name = "issued_invoices")
@Data
public class IssuedInvoice
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company", nullable = false)
    private InsurancePolicyCompany company;

    @Column(name = "value", nullable = false)
    private Long value;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private IssuedInvoiceStatus status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_service_id")
    private OrderService orderService;
}