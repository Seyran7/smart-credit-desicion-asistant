package com.seyran.scda.ai.rule;

import com.seyran.scda.entity.CreditApplication;
import com.seyran.scda.enums.LoanPurpose;
import org.springframework.stereotype.Component;

@Component
public class PurposeRule implements CreditRule {

    @Override
    public RuleResult evaluate(CreditApplication application) {

        LoanPurpose purpose = application.getPurpose();

        switch (purpose) {

            case HOME:
                return RuleResult.builder()
                        .ruleName("Kredit təyinatı qaydası")
                        .score(15)
                        .passed(true)
                        .explanation("Mənzil krediti uzunmüddətli və aşağı riskli kredit hesab olunur.")
                        .strength("Kredit məqsədi aşağı riskli hesab olunur.")
                        .build();

            case EDUCATION:
                return RuleResult.builder()
                        .ruleName("Kredit təyinatı qaydası")
                        .score(12)
                        .passed(true)
                        .explanation("Təhsil krediti müsbət sosial təsirə malik olduğundan risk aşağı qiymətləndirildi.")
                        .strength("Təhsil krediti etibarlı məqsəd hesab olunur.")
                        .build();

            case BUSINESS:
                return RuleResult.builder()
                        .ruleName("Kredit təyinatı qaydası")
                        .score(8)
                        .passed(true)
                        .explanation("Biznes krediti əlavə qiymətləndirmə tələb etsə də qəbul edilə biləndir.")
                        .strength("Biznes məqsədi qəbul ediləndir.")
                        .build();

            case AUTO:
                return RuleResult.builder()
                        .ruleName("Kredit təyinatı qaydası")
                        .score(7)
                        .passed(true)
                        .explanation("Avtomobil krediti standart risk kateqoriyasına daxildir.")
                        .strength("Avtomobil krediti qəbul ediləndir.")
                        .build();

            case CONSUMER:
                return RuleResult.builder()
                        .ruleName("Kredit təyinatı qaydası")
                        .score(5)
                        .passed(true)
                        .explanation("İstehlak krediti standart risk kateqoriyasında qiymətləndirildi.")
                        .strength("İstehlak krediti qəbul ediləndir.")
                        .build();

            case OTHER:
            default:
                return RuleResult.builder()
                        .ruleName("Kredit təyinatı qaydası")
                        .score(-5)
                        .passed(false)
                        .explanation("Kredit məqsədi əlavə araşdırma tələb edir.")
                        .risk("Kredit məqsədi qeyri-müəyyəndir.")
                        .build();
        }
    }
}