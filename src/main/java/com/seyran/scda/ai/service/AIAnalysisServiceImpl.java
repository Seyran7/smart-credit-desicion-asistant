package com.seyran.scda.ai.service;

import com.seyran.scda.ai.engine.CreditDecisionRuleEngine;
import com.seyran.scda.ai.llm.LLMAnalysisService;
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

        AIAnalysis analysis = ruleEngine.analyze(application);

        analysis.setExplanation(
                llmAnalysisService.generateExplanation(
                        application,
                        analysis
                )
        );

        return analysis;
    }
}