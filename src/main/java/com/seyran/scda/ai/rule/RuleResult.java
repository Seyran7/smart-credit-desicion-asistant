package com.seyran.scda.ai.rule;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RuleResult {

    private String ruleName;

    private int score;

    private boolean passed;

    private String explanation;

    private String strength;

    private String risk;

}
