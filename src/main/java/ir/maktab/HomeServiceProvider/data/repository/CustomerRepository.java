package ir.maktab.HomeServiceProvider.data.repository;

import ir.maktab.HomeServiceProvider.data.model.BaseService;
import ir.maktab.HomeServiceProvider.data.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUsername(String username);

    Optional<Customer> findByEmailAddress(String emailAddress);

    @Query("select c from Customer c where c.isDeleted=?1")
    List<Customer> findAllByDeletedIs(@Param("is_deleted") boolean isDeleted);
}
