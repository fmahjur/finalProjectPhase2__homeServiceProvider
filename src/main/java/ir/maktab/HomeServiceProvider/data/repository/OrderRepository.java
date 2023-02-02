package ir.maktab.HomeServiceProvider.data.repository;

import ir.maktab.HomeServiceProvider.data.model.BaseService;
import ir.maktab.HomeServiceProvider.data.model.Customer;
import ir.maktab.HomeServiceProvider.data.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findAllByCustomer(Customer customer);

    Optional<Orders> findByOrderNumber(String orderNumber);

    @Query("select o from Orders o where o.isDeleted=?1")
    List<Orders> findAllByDeletedIs(@Param("is_deleted") boolean isDeleted);
}
