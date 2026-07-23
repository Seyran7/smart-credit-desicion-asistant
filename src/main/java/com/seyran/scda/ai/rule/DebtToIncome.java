package com.seyran.scda.ai.rule;

import com.seyran.scda.entity.CreditApplication;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DebtToIncome implements CreditRule{

    @Override
    public RuleResult evaluate(CreditApplication application) {

        BigDecimal income = application.getMonthlyIncome();
        BigDecimal debt = application.getExistingMonthlyDebt();


        if (income.compareTo(BigDecimal.ZERO) <= 0) {

            return RuleResult.builder()
                    .ruleName("Borc / Gəlir nisbəti (DTI)")
                    .score(-30)
                    .passed(false)
                    .explanation("Aylıq gəlir düzgün göstərilmədiyi üçün DTI hesablana bilmədi.")
                    .risk("Aylıq gəlir etibarlı deyil.")
                    .build();
        }

    BigDecimal dti = debt
            .multiply(BigDecimal.valueOf(100))
            .divide(income, 2, RoundingMode.HALF_UP);

        if (dti.compareTo(BigDecimal.valueOf(30)) <= 0) {

        return RuleResult.builder()
                .ruleName("Borc / Gəlir nisbəti (DTI)")
                .score(20)
                .passed(true)
                .explanation("Borc/Gəlir nisbəti " + dti + "% təşkil edir və təhlükəsiz hesab olunur.")
                .strength("Müştərinin mövcud borc yükü aşağıdır.")
                .build();
    }

        if (dti.compareTo(BigDecimal.valueOf(50)) <= 0) {

            return RuleResult.builder()
                    .ruleName("Borc / Gəlir nisbəti (DTI)")
                    .score(10)
                    .passed(true)
                    .explanation("Borc/Gəlir nisbəti " + dti + "% təşkil edir və qəbul edilə bilən səviyyədədir.")
                    .strength("Müştərinin borc yükü idarə oluna biləndir.")
                    .build();
        }

        if (dti.compareTo(BigDecimal.valueOf(70)) <= 0) {

            return RuleResult.builder()
                    .ruleName("Borc / Gəlir nisbəti (DTI)")
                    .score(-10)
                    .passed(false)
                    .explanation("Borc/Gəlir nisbəti " + dti + "% təşkil edir və risk yaradır.")
                    .risk("Müştərinin mövcud borc yükü yüksəkdir.")
                    .build();
        }

        return RuleResult.builder()
                .ruleName("Borc / Gəlir nisbəti (DTI)")
                .score(-25)
                .passed(false)
                .explanation("Borc/Gəlir nisbəti " + dti + "% təşkil edir və çox yüksək risk yaradır.")
                .risk("Müştərinin mövcud borc yükü kritik səviyyədədir.")
                .build();
    }
}
