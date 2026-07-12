package com.seyran.scda.repository;

import com.seyran.scda.entity.CreditApplication;
import com.seyran.scda.enums.AIRecommendation;
import com.seyran.scda.enums.ApplicationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CreditApplicationRepository
        extends JpaRepository<CreditApplication, Long>,
        JpaSpecificationExecutor<CreditApplication> {

    boolean existsByFinCode(String finCode);

    Optional<CreditApplication> findByFinCode(String finCode);

    Page<CreditApplication> findByStatus(
            ApplicationStatus status,
            Pageable pageable);

    Page<CreditApplication> findByLastNameContainingIgnoreCase(
            String lastName,
            Pageable pageable);

    @Query("""
            SELECT COUNT(c)
            FROM CreditApplication c
            WHERE c.aiAnalysis.recommendation = :recommendation
            """)
    long countByRecommendation(AIRecommendation recommendation);

    @Query("""
            SELECT AVG(c.aiAnalysis.confidenceScore)
            FROM CreditApplication c
            WHERE c.aiAnalysis IS NOT NULL
            """)
    Double averageConfidence();

    @Query("""
            SELECT AVG(c.aiAnalysis.riskScore)
            FROM CreditApplication c
            WHERE c.aiAnalysis IS NOT NULL
            """)
    Double averageRisk();

}