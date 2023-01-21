package ir.maktab.HomeServiceProvider.data.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class SubService extends BaseEntity implements Service {
    @ManyToOne
    BaseService baseService;
    String name;
    String description;
    Double basePrice;

    @ManyToMany(mappedBy = "subServices")
    Set<Expert> experts = new HashSet<>();

    @Column(columnDefinition = "boolean default false")
    boolean isDeleted;

    public SubService(BaseService baseService, String name, String description, Double basePrice) {
        this.baseService = baseService;
        this.name = name;
        this.description = description;
        this.basePrice = basePrice;
    }

    public void showServiceDetails() {
        System.out.println("name: " + name + " | " +
                "description: " + description + " | " +
                "basePrice: " + basePrice);
    }
}
