package ir.maktab.HomeServiceProvider.data.model;

import ir.maktab.HomeServiceProvider.data.enums.OfferStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
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

    @Temporal(TemporalType.TIMESTAMP)
    Date proposedStartDate;

    int durationOfWork;

    OfferStatus offerStatus;

    @Column(columnDefinition = "boolean default false")
    boolean isDeleted;

    public Offer() {
        this.offerStatus = OfferStatus.WAITING;
    }
}
