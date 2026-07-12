package com.seyran.scda.ai.rule;

import com.seyran.scda.entity.CreditApplication;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class LoanAmountRule implements CreditRule {

    @Override
    public RuleResult evaluate(CreditApplication application) {

        if (application.getRequestedLoanAmount()
                .compareTo(BigDecimal.valueOf(15000)) <= 0) {

            return RuleResult.builder()
                    .ruleName("Loan Amount Rule")
                    .score(15)
                    .strength("Requested loan amount is acceptable.")
                    .build();
        }

        return RuleResult.builder()
                .ruleName("Loan Amount Rule")
                .score(-15)
                .risk("Requested loan amount is high.")
                .build();
    }
}