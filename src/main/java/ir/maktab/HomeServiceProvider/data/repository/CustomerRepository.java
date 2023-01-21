package ir.maktab.HomeServiceProvider.data.repository;

import ir.maktab.HomeServiceProvider.data.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
