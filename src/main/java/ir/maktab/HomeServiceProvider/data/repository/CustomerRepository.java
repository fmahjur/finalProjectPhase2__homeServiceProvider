package repository;

import ir.maktab.HomeServiceProvider.data.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUsername(String username);
    Customer findByEmailAddress(String emailAddress);

}
