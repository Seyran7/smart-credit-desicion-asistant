package com.seyran.scda.ai.explanation;

import com.seyran.scda.entity.AIAnalysis;
import org.springframework.stereotype.Component;

@Component
public class AIExplanationGenerator {

    public String generate(AIAnalysis analysis) {

        StringBuilder sb = new StringBuilder();

        sb.append("AI Recommendation: ")
                .append(analysis.getRecommendation())
                .append(". ");

        if (analysis.getStrengths() != null &&
                !analysis.getStrengths().isBlank()) {

            sb.append("Strengths: ")
                    .append(analysis.getStrengths())
                    .append(". ");
        }

        if (analysis.getRisks() != null &&
                !analysis.getRisks().isBlank()) {

            sb.append("Risks: ")
                    .append(analysis.getRisks())
                    .append(". ");
        }

        return sb.toString();
    }
}