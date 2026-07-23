package com.seyran.scda.ai.rule;


import com.seyran.scda.entity.CreditApplication;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class MonthlyInstallmentRule implements CreditRule{

    @Override
    public RuleResult evaluate(CreditApplication application) {

        BigDecimal monthlyIncome = application.getMonthlyIncome();

        BigDecimal existingDebt = application.getExistingMonthlyDebt();

        BigDecimal monthlyInstallment = application
                .getRequestedLoanAmount()
                .divide(
                        BigDecimal.valueOf(application.getLoanTermMonths()),
                        2,
                        RoundingMode.HALF_UP
                );

        BigDecimal totalMonthlyDebt = existingDebt.add(monthlyInstallment);

        BigDecimal ratio = totalMonthlyDebt
                .multiply(BigDecimal.valueOf(100))
                .divide(monthlyIncome, 2, RoundingMode.HALF_UP);

        if (ratio.compareTo(BigDecimal.valueOf(40)) <= 0) {

            return RuleResult.builder()
                    .ruleName("Aylıq ödəniş yükü qaydası")
                    .score(20)
                    .passed(true)
                    .explanation(
                            "Yeni kreditdən sonra aylıq öhdəlik gəlirin "
                                    + ratio +
                                    "% təşkil edir."
                    )
                    .strength("Müştərinin aylıq kredit yükü təhlükəsiz səviyyədədir.")
                    .build();
        }

        if (ratio.compareTo(BigDecimal.valueOf(60)) <= 0) {

            return RuleResult.builder()
                    .ruleName("Aylıq ödəniş yükü qaydası")
                    .score(5)
                    .passed(true)
                    .explanation(
                            "Yeni kreditdən sonra aylıq öhdəlik gəlirin "
                                    + ratio +
                                    "% təşkil edir."
                    )
                    .strength("Aylıq ödəniş mümkündür, lakin əlavə qiymətləndirmə tövsiyə olunur.")
                    .build();
        }

        return RuleResult.builder()
                .ruleName("Aylıq ödəniş yükü qaydası")
                .score(-25)
                .passed(false)
                .explanation(
                        "Yeni kreditdən sonra aylıq öhdəlik gəlirin "
                                + ratio +
                                "% təşkil edir."
                )
                .risk("Aylıq kredit öhdəliyi gəlirə nisbətən çox yüksəkdir.")
                .build();
    }
}
