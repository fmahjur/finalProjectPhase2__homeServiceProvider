package ir.maktab.HomeServiceProvider.data.repository;

import ir.maktab.HomeServiceProvider.data.model.BaseService;
import ir.maktab.HomeServiceProvider.data.model.Expert;
import ir.maktab.HomeServiceProvider.data.model.Offer;
import ir.maktab.HomeServiceProvider.data.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findAllByOrdersIs(Orders orders);

    List<Offer> findAllByExpert(Expert expert);

    @Query("select o from Offer o where o.isDeleted=?1")
    List<Offer> findAllByDeletedIs(@Param("is_deleted") boolean isDeleted);
}
