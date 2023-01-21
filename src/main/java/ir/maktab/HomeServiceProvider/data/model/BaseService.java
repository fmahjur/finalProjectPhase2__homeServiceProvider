package ir.maktab.HomeServiceProvider.data.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class BaseService extends BaseEntity implements Service {
    String name;
    @OneToMany(mappedBy = "baseService")
    List<SubService> subServiceList = new ArrayList<>();

    @Column(columnDefinition = "boolean default false")
    boolean isDeleted;

    public BaseService(String name) {
        this.name = name;
    }

    public void addSubService(SubService subService) {
        subServiceList.add(subService);
    }

    public void removeSubService(SubService subService) {
        subServiceList.remove(subService);
    }

    public void showServiceDetails() {
        System.out.println("Service name: " + name);
        for (SubService sub : subServiceList) {
            sub.showServiceDetails();
        }
    }
}
