package com.seyran.scda.ai.llm;

import com.seyran.scda.ai.model.LLMResult;
import com.seyran.scda.entity.AIAnalysis;
import com.seyran.scda.entity.CreditApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("mock")
public class MockLLMAnalysisService implements LLMAnalysisService {


    @Override
    public LLMResult generateExplanation(
            CreditApplication application,
            AIAnalysis analysis
    ) {

        return LLMResult.builder()
                .recommendation(analysis.getRecommendation())
                .confidenceScore(analysis.getConfidenceScore())
                .riskScore(analysis.getRiskScore())
                .reason(
                        "Mock AI explanation: Customer analysis completed successfully."
                )
                .strengths(
                        analysis.getStrengths()
                )
                .weaknesses(
                        analysis.getRisks()
                )
                .advice(
                        "Review customer documents before final approval."
                )
                .build();
    }
}