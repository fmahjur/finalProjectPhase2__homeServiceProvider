package ir.maktab.HomeServiceProvider.data.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Customer extends Account {
    @OneToMany(mappedBy = "customer")
    List<Order> orders;

    @Column(columnDefinition = "boolean default false")
    boolean isDeleted;
}
