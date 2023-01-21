package ir.maktab.HomeServiceProvider.data.repository;

import ir.maktab.HomeServiceProvider.data.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

}
