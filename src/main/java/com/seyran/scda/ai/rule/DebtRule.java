package com.seyran.scda.ai.rule;

import com.seyran.scda.entity.CreditApplication;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class DebtRule implements CreditRule {

    @Override
    public RuleResult evaluate(CreditApplication application) {

        BigDecimal debtRatio = application.getExistingMonthlyDebt()
                .divide(application.getMonthlyIncome(), 2, RoundingMode.HALF_UP);

        if (debtRatio.compareTo(new BigDecimal("0.40")) <= 0) {
            return RuleResult.builder()
                    .score(20)
                    .strength("Debt ratio is acceptable.")
                    .build();
        }

        return RuleResult.builder()
                .score(-20)
                .risk("Debt ratio is too high.")
                .build();
    }
}
