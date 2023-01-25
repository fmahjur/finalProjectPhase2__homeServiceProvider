package ir.maktab.HomeServiceProvider.data.repository;

import ir.maktab.HomeServiceProvider.data.model.Customer;
import ir.maktab.HomeServiceProvider.data.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByCustomer(Customer customer);
    Order findByOrderNumber(String orderNumber);
}
