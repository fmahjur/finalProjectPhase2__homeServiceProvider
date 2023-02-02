package ir.maktab.HomeServiceProvider.data.repository;

import ir.maktab.HomeServiceProvider.data.model.BaseService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BaseServiceRepository extends JpaRepository<BaseService, Long> {
    Optional<BaseService> findByName(String baseServiceName);

    @Query("select b from BaseService b where b.isDeleted=?1")
    List<BaseService> findAllByDeletedIs(@Param("is_deleted") boolean isDeleted);
}
