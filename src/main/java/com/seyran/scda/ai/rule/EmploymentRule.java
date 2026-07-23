package com.seyran.scda.ai.rule;

import com.seyran.scda.entity.CreditApplication;
import org.springframework.stereotype.Component;

@Component
public class EmploymentRule implements CreditRule {

    @Override
    public RuleResult evaluate(CreditApplication application) {

        int months = application.getEmploymentMonths();

        if (months >= 60) {
            return RuleResult.builder()
                    .ruleName("İş stajı qaydası")
                    .score(25)
                    .passed(true)
                    .explanation("Müştərinin iş stajı 5 ildən çoxdur. Bu, yüksək etibarlılıq göstəricisidir.")
                    .strength("Müştərinin uzunmüddətli iş stajı mövcuddur.")
                    .build();
        }

        if (months >= 36) {
            return RuleResult.builder()
                    .ruleName("İş stajı qaydası")
                    .score(20)
                    .passed(true)
                    .explanation("Müştərinin iş stajı 3 ildən çoxdur.")
                    .strength("Müştərinin sabit iş stajı mövcuddur.")
                    .build();
        }

        if (months >= 24) {
            return RuleResult.builder()
                    .ruleName("İş stajı qaydası")
                    .score(15)
                    .passed(true)
                    .explanation("İş stajı bankın minimum tələblərindən yüksəkdir.")
                    .strength("İş stajı qənaətbəxşdir.")
                    .build();
        }

        if (months >= 12) {
            return RuleResult.builder()
                    .ruleName("İş stajı qaydası")
                    .score(5)
                    .passed(true)
                    .explanation("İş stajı minimum səviyyədə qənaətbəxşdir.")
                    .strength("İş stajı kifayət qədərdir.")
                    .build();
        }

        if (months >= 6) {
            return RuleResult.builder()
                    .ruleName("İş stajı qaydası")
                    .score(-5)
                    .passed(false)
                    .explanation("İş stajı qısadır və əlavə risk yaradır.")
                    .risk("Müştərinin iş stajı qısadır.")
                    .build();
        }

        return RuleResult.builder()
                .ruleName("İş stajı qaydası")
                .score(-15)
                .passed(false)
                .explanation("İş stajı bankın minimum tələblərinə cavab vermir.")
                .risk("Müştərinin iş stajı çox azdır.")
                .build();
    }
}