package ir.maktab.HomeServiceProvider.data.repository;

import ir.maktab.HomeServiceProvider.data.enums.ExpertStatus;
import ir.maktab.HomeServiceProvider.data.model.BaseService;
import ir.maktab.HomeServiceProvider.data.model.Expert;
import ir.maktab.HomeServiceProvider.data.model.SubService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ExpertRepository extends JpaRepository<Expert, Long> {
    boolean existsExpertByUsernameAndExpertStatusIs(String username, ExpertStatus expertStatus);

    boolean existsByEmailAddress(String emailAddress);

    Optional<Expert> findByUsername(String username);

    Optional<Expert> findByEmailAddress(String emailAddress);

    List<Expert> findAllByExpertStatus(ExpertStatus expertStatus);

    @Query("select e from Expert e where e.isDeleted=?1")
    List<Expert> findAllByDeletedIs(@Param("is_deleted") boolean isDeleted);

    @Modifying(clearAutomatically = true)
    @Query("update Expert e set e.subServices=?1 where e.username =?2")
    @Transactional
    void updateExpertSubService(@Param("subServices") Set<SubService> subServices, @Param("username") String username);
}
