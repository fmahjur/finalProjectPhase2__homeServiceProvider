package ir.maktab.HomeServiceProvider.data.repository;

import ir.maktab.HomeServiceProvider.data.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
