package com.seyran.scda.ai.llm;

import com.seyran.scda.entity.AIAnalysis;
import com.seyran.scda.entity.CreditApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MockLLMAnalysisService implements LLMAnalysisService {

    @Override
    public String generateExplanation(
            CreditApplication application,
            AIAnalysis analysis) {

        log.info("Generating AI explanation using Mock LLM...");

        StringBuilder sb = new StringBuilder();

        sb.append("Customer ")
                .append(application.getFirstName())
                .append(" ")
                .append(application.getLastName())
                .append(" has been analyzed by the Smart Credit Decision Assistant. ");

        sb.append("AI recommendation is ")
                .append(analysis.getRecommendation())
                .append(". ");

        sb.append("Confidence score is ")
                .append(analysis.getConfidenceScore())
                .append("% while calculated risk score is ")
                .append(analysis.getRiskScore())
                .append("%. ");

        if (analysis.getStrengths() != null &&
                !analysis.getStrengths().isBlank()) {

            sb.append("Strengths detected: ")
                    .append(analysis.getStrengths().replace("\n", " "));
        }

        if (analysis.getRisks() != null &&
                !analysis.getRisks().isBlank()) {

            sb.append("Potential risks: ")
                    .append(analysis.getRisks().replace("\n", " "));
        }

        sb.append(" Final recommendation should always be confirmed by a credit specialist.");

        return sb.toString();
    }

}