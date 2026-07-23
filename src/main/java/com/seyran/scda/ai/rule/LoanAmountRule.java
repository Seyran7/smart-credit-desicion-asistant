package com.seyran.scda.ai.rule;

import com.seyran.scda.entity.CreditApplication;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class LoanAmountRule implements CreditRule {

    @Override
    public RuleResult evaluate(CreditApplication application) {

        BigDecimal income = application.getMonthlyIncome();
        BigDecimal loan = application.getRequestedLoanAmount();

        BigDecimal ratio = loan.divide(income, 2, BigDecimal.ROUND_HALF_UP);

        if (ratio.compareTo(BigDecimal.valueOf(5)) <= 0) {

            return RuleResult.builder()
                    .ruleName("Kredit məbləği qaydası")
                    .score(20)
                    .passed(true)
                    .explanation("İstənilən kredit məbləği müştərinin gəliri ilə uyğun hesab edilir.")
                    .strength("Kredit məbləği müştərinin maliyyə imkanlarına uyğundur.")
                    .build();
        }

        if (ratio.compareTo(BigDecimal.valueOf(8)) <= 0) {

            return RuleResult.builder()
                    .ruleName("Kredit məbləği qaydası")
                    .score(5)
                    .passed(true)
                    .explanation("Kredit məbləği qəbul edilə bilər, lakin əlavə qiymətləndirmə tələb olunur.")
                    .strength("Kredit məbləği nəzarət altında qəbul edilə bilər.")
                    .build();
        }

        return RuleResult.builder()
                .ruleName("Kredit məbləği qaydası")
                .score(-20)
                .passed(false)
                .explanation("İstənilən kredit məbləği müştərinin gəlirinə nisbətən yüksəkdir.")
                .risk("Kredit məbləği müştərinin gəlirinə görə yüksək hesab olunur.")
                .build();
    }

}