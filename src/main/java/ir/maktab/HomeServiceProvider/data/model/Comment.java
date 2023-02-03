package ir.maktab.HomeServiceProvider.data.model;

import jakarta.persistence.*;
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

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="expert_id")
    Expert expert;

    @Column(columnDefinition = "boolean default false")
    boolean isDeleted;
}
