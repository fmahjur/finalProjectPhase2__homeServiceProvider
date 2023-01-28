package ir.maktab.HomeServiceProvider.data.repository;

import ir.maktab.HomeServiceProvider.data.enums.ExpertStatus;
import ir.maktab.HomeServiceProvider.data.model.Expert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExpertRepository extends JpaRepository<Expert, Long> {
    Optional<Expert> findByUsername(String username);

    Optional<Expert> findByEmailAddress(String emailAddress);

    List<Expert> findAllByExpertStatus(ExpertStatus expertStatus);
}
