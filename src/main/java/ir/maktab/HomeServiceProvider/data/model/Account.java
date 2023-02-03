package ir.maktab.HomeServiceProvider.data.model;

import ir.maktab.HomeServiceProvider.Utils.DateUtil;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@MappedSuperclass
public abstract class Account extends Person {
    @Column(unique = true)
    String username;

    String password;
    @OneToOne(cascade = {CascadeType.ALL})
    Credit credit;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    Date registeryDate;

    public Account(String firstname, String lastname, String emailAddress) {
        super(firstname, lastname, emailAddress);
        this.username = emailAddress;
    }

    public Account(String firstname, String lastname, String emailAddress, String password, Credit credit) {
        super(firstname, lastname, emailAddress);
        this.username = emailAddress;
        this.password = password;
        this.credit = credit;
        this.registeryDate = DateUtil.asDate(LocalDateTime.now());
    }
}
