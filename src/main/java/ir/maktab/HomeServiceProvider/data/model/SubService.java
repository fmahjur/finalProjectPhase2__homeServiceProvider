package ir.maktab.HomeServiceProvider.data.model;

import jakarta.persistence.*;
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
    @ManyToOne(cascade = {CascadeType.ALL})
    BaseService baseService;

    @Column(unique = true)
    String name;

    String description;

    Double basePrice;

    @ManyToMany(mappedBy = "subServices", targetEntity = Expert.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
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
