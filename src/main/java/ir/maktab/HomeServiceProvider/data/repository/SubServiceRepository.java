package repository;

import ir.maktab.HomeServiceProvider.data.model.BaseService;
import ir.maktab.HomeServiceProvider.data.model.SubService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubServiceRepository extends JpaRepository<SubService, Long> {
    SubService findByName(String subService);

    List<SubService> findAllByBaseService(BaseService baseService);
}
