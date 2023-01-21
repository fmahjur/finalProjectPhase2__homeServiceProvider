package ir.maktab.HomeServiceProvider.data.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Comment extends BaseEntity {
    String comment;
    double score;

    @ManyToOne
    Expert expert;

    @Column(columnDefinition = "boolean default false")
    boolean isDeleted;
}
