package ir.maktab.HomeServiceProvider.data.model;

import ir.maktab.HomeServiceProvider.data.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    String orderNumber;
    @ManyToOne
    Customer customer;

    @ManyToOne
    SubService subService;

    @OneToMany(mappedBy = "order")
    Set<Offer> offers;

    @Column(nullable = false)
    String description;

    @Column(nullable = false)
    Long CustomerProposedPrice;

    @Enumerated(EnumType.STRING)
    OrderStatus orderStatus;

    @OneToOne
    Comment comment;

    @Column(columnDefinition = "boolean default false")
    boolean isDeleted;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    Date orderRegistrationDate;

    @Temporal(TemporalType.TIMESTAMP)
    Date workStartDate;

    DurationOfWork durationOfWork;

    @OneToOne
    Address address;

    public Order() {
        this.orderStatus = OrderStatus.WATING_FOR_EXPERTS_OFFER;
    }
}
