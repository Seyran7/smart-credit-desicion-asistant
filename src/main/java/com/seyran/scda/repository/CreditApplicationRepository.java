package com.seyran.scda.repository;

import com.seyran.scda.entity.CreditApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CreditApplicationRepository extends JpaRepository<CreditApplication, Long> {
    boolean existsByFinCode(String finCode);

    Optional<CreditApplication> findByFinCode(String finCode);
}
