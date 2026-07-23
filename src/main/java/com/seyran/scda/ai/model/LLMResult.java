package com.seyran.scda.ai.model;

import com.seyran.scda.enums.AIRecommendation;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LLMResult {

    private AIRecommendation recommendation;

    private Integer confidenceScore;

    private Integer riskScore;

    private String reason;

    private String strengths;

    private String weaknesses;

    private String advice;

}