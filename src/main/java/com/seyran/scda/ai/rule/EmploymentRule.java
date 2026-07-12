package com.seyran.scda.ai.rule;

import com.seyran.scda.entity.CreditApplication;
import org.springframework.stereotype.Component;

@Component
public class EmploymentRule implements CreditRule {

    @Override
    public RuleResult evaluate(CreditApplication application) {

        if (application.getEmploymentMonths() >= 24) {

            return RuleResult.builder()
                    .score(20)
                    .strength("Stable employment history.")
                    .build();
        }

        return RuleResult.builder()
                .score(-10)
                .risk("Employment history is too short.")
                .build();
    }
}