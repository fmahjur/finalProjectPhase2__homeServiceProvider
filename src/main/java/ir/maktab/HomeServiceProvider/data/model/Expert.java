package ir.maktab.finalprojectphase2.data.model;

import ir.maktab.HomeServiceProvider.model.enums.ExpertStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Expert extends Account {
    @Enumerated(EnumType.STRING)
    ExpertStatus expertStatus;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    byte[] personalPhoto;

    @ManyToMany
    Set<SubService> subServices = new HashSet<>();

    double rate;

    @OneToMany(mappedBy = "expert")
    Set<Comment> comment = new HashSet<>();

    @Column(columnDefinition = "boolean default false")
    boolean isDeleted;

    public void setSubServices(SubService subServices) {
        this.subServices.add(subServices);
    }

    public Expert() {
        this.rate = 0;
    }

    public void setRate(double rate) {
        double sum = 0;
        for (Comment coment :
                comment) {
            sum += coment.getScore();
        }
        this.rate = sum / comment.size();
    }
}
