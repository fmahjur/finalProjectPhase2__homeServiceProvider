package ir.maktab.finalprojectphase2.data.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Offer extends BaseEntity {
    @OneToOne
    Expert expert;

    @ManyToOne
    Orders orders;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    Date offerDate;

    Double offerPrice;

    DurationOfWork durationOfWork;

    @Column(columnDefinition = "boolean default false")
    boolean isDeleted;
}
