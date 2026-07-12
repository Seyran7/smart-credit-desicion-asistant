package com.seyran.scda.ai.rule;

import com.seyran.scda.entity.CreditApplication;
import com.seyran.scda.enums.LoanPurpose;
import org.springframework.stereotype.Component;

@Component
public class PurposeRule implements CreditRule {

    @Override
    public RuleResult evaluate(CreditApplication application) {

        if (application.getPurpose() == LoanPurpose.HOME
                || application.getPurpose() == LoanPurpose.EDUCATION) {

            return RuleResult.builder()
                    .ruleName("Purpose Rule")
                    .score(10)
                    .strength("Loan purpose is considered low risk.")
                    .build();
        }

        return RuleResult.builder()
                .ruleName("Purpose Rule")
                .score(5)
                .strength("Loan purpose is acceptable.")
                .build();
    }
}