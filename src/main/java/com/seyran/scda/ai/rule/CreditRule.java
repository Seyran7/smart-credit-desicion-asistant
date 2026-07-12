package com.seyran.scda.ai.rule;

import com.seyran.scda.entity.CreditApplication;

public interface CreditRule {
    RuleResult evaluate(CreditApplication application);

}
