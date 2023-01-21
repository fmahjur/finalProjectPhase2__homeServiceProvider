package ir.maktab.HomeServiceProvider.data.model;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@MappedSuperclass
public abstract class Account extends Person {
    String username;
    String password;
    @OneToOne
    Credit credit;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    Date registeryDate;

    public Account(String firstname, String lastname, String emailAddress, String username) {
        super(firstname, lastname, emailAddress);
        this.username = emailAddress;
    }
}
