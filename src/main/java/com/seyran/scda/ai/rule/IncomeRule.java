package com.seyran.scda.ai.rule;

import com.seyran.scda.entity.CreditApplication;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class IncomeRule implements CreditRule {

    @Override
    public RuleResult evaluate(CreditApplication application) {

        BigDecimal income = application.getMonthlyIncome();

        if (income.compareTo(BigDecimal.valueOf(3000)) >= 0) {

            return RuleResult.builder()
                    .ruleName("Income Rule")
                    .score(25)
                    .passed(true)
                    .explanation("Monthly income is significantly above the required threshold.")
                    .strength("Monthly income is excellent.")
                    .build();
        }

        if (income.compareTo(BigDecimal.valueOf(2000)) >= 0) {

            return RuleResult.builder()
                    .ruleName("Income Rule")
                    .score(15)
                    .passed(true)
                    .explanation("Monthly income meets the minimum acceptable threshold.")
                    .strength("Monthly income is acceptable.")
                    .build();
        }

        return RuleResult.builder()
                .ruleName("Income Rule")
                .score(-20)
                .passed(false)
                .explanation("Monthly income is below the minimum acceptable threshold.")
                .risk("Monthly income is too low.")
                .build();
    }
}