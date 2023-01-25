package ir.maktab.HomeServiceProvider.data.repository;

import ir.maktab.HomeServiceProvider.data.model.BaseService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseServiceRepository extends JpaRepository<BaseService, Long> {
    BaseService findByName(String baseServiceName);
}
