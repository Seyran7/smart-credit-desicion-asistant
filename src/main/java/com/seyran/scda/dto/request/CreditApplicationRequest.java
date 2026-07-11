package com.seyran.scda.dto.request;

import com.seyran.scda.enums.LoanPurpose;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditApplicationRequest {
    @NotBlank(message = "First name cannot be blank")
    @Size(min=2,max=50,message = "First name must be between 2 and 50 characters")
    private String firstName;
    @NotBlank(message = "Last name can not be blank")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;
    @NotBlank(message = "FIN code can not be blank")
    @Size(min =7,max=7,message = "FIN code must contain exactly 7 characters")
    private String finCode;
    @NotNull(message = "Monthly income is required")
    @Positive(message = "Monthly income must be greater than zero")
    private BigDecimal monthlyIncome;
    @NotNull(message = "Employment months is required")
    @PositiveOrZero(message = "Employment months cannot be negative")
    private Integer employmentMonths;
    @NotNull(message = "Existing monthly debt is required")
    @PositiveOrZero(message = "Existing monthly debt cannot be negative")
    private BigDecimal requestedLoanAmount;
    @NotNull(message = "Requested loan amount is required")
    @Positive(message = "Requested loan amount must be greater than zero")
    private BigDecimal existingMonthlyDebt;
    @NotNull(message = "Loan term is required")
    @Positive(message = "Loan term must be greater than zero")
    private Integer loanTermMonths;
    @NotNull(message = "Loan purpose is required")
    private LoanPurpose purpose;
}
