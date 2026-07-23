package com.seyran.scda.ai.rule;

import com.seyran.scda.entity.CreditApplication;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class IncomeRule implements CreditRule {

    @Override
    public RuleResult evaluate(CreditApplication application) {

        BigDecimal income = application.getMonthlyIncome();

        if (income.compareTo(BigDecimal.valueOf(4000)) >= 0) {
            return RuleResult.builder()
                    .ruleName("Aylıq gəlir qaydası")
                    .score(30)
                    .passed(true)
                    .explanation("Müştərinin aylıq gəliri çox yüksəkdir.")
                    .strength("Aylıq gəlir çox yüksəkdir.")
                    .build();
        }

        if (income.compareTo(BigDecimal.valueOf(3000)) >= 0) {
            return RuleResult.builder()
                    .ruleName("Aylıq gəlir qaydası")
                    .score(25)
                    .passed(true)
                    .explanation("Aylıq gəlir yüksək səviyyədədir.")
                    .strength("Aylıq gəlir yüksəkdir.")
                    .build();
        }

        if (income.compareTo(BigDecimal.valueOf(2000)) >= 0) {
            return RuleResult.builder()
                    .ruleName("Aylıq gəlir qaydası")
                    .score(15)
                    .passed(true)
                    .explanation("Aylıq gəlir bankın tələblərinə uyğundur.")
                    .strength("Aylıq gəlir qənaətbəxşdir.")
                    .build();
        }

        if (income.compareTo(BigDecimal.valueOf(1500)) >= 0) {
            return RuleResult.builder()
                    .ruleName("Aylıq gəlir qaydası")
                    .score(5)
                    .passed(true)
                    .explanation("Aylıq gəlir minimal səviyyədə uyğundur.")
                    .strength("Aylıq gəlir minimum tələblərə cavab verir.")
                    .build();
        }

        if (income.compareTo(BigDecimal.valueOf(1000)) >= 0) {
            return RuleResult.builder()
                    .ruleName("Aylıq gəlir qaydası")
                    .score(-10)
                    .passed(false)
                    .explanation("Aylıq gəlir kredit ödənişləri üçün risk yarada bilər.")
                    .risk("Müştərinin aylıq gəliri aşağıdır.")
                    .build();
        }

        return RuleResult.builder()
                .ruleName("Aylıq gəlir qaydası")
                .score(-20)
                .passed(false)
                .explanation("Aylıq gəlir bankın minimum tələblərindən aşağıdır.")
                .risk("Müştərinin aylıq gəliri çox aşağıdır.")
                .build();
    }
}