package com.seyran.scda.dto.response;

import com.seyran.scda.enums.ApplicationStatus;
import com.seyran.scda.enums.LoanPurpose;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditApplicationResponse {

    private AIAnalysisResponse aiAnalysis;

    private Long  id;
    private String firstName;
    private String lastName;
    private String finCode;
    private BigDecimal monthlyIncome;
    private Integer employmentMonths;
    private BigDecimal requestedLoanAmount;
    private BigDecimal existingMonthlyDebt;
    private Integer loanTermMonths;
    private LoanPurpose purpose;
    private ApplicationStatus status;
    private LocalDateTime createdAt;
}
