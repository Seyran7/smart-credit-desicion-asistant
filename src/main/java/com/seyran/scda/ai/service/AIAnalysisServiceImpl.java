package com.seyran.scda.ai.service;

import com.seyran.scda.ai.engine.CreditDecisionRuleEngine;
import com.seyran.scda.ai.llm.LLMAnalysisService;
import com.seyran.scda.ai.model.LLMResult;
import com.seyran.scda.entity.AIAnalysis;
import com.seyran.scda.entity.CreditApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AIAnalysisServiceImpl implements AIAnalysisService {


    private final CreditDecisionRuleEngine ruleEngine;
    private final LLMAnalysisService llmAnalysisService;


    @Override
    public AIAnalysis analyze(CreditApplication application) {


        // 1. Java rule engine qərar verir
        AIAnalysis analysis = ruleEngine.analyze(application);


        // 2. Groq AI əlavə izah və analiz yaradır
        LLMResult llmResult =
                llmAnalysisService.generateExplanation(
                        application,
                        analysis
                );


        // 3. LLM nəticəsini entity-yə yazırıq

        analysis.setExplanation(
                llmResult.getReason()
        );

        analysis.setRecommendation(
                llmResult.getRecommendation()
        );

        analysis.setConfidenceScore(
                llmResult.getConfidenceScore()
        );

        analysis.setRiskScore(
                llmResult.getRiskScore()
        );

        analysis.setStrengths(
                llmResult.getStrengths()
        );

        analysis.setRisks(
                llmResult.getWeaknesses()
        );


        return analysis;
    }
}