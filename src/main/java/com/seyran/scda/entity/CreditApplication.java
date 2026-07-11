package com.seyran.scda.entity;

import com.seyran.scda.enums.ApplicationStatus;
import com.seyran.scda.enums.LoanPurpose;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="credit_applications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true,length = 7)
    private String finCode;

    @Column(nullable = false)
    private BigDecimal monthlyIncome;

    @Column(nullable = false)
    private Integer employmentMonths;

    @Column(nullable = false)
    private BigDecimal existingMonthlyDebt;

    @Column(nullable = false)
    private BigDecimal requestedLoanAmount;

    @Column(nullable = false)
    private Integer loanTermMonths;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanPurpose purpose;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status;

    @Column(length = 1000)
    private String operatorNote;


    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
