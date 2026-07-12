package com.seyran.scda.ai.llm;

import com.seyran.scda.entity.AIAnalysis;
import com.seyran.scda.entity.CreditApplication;

public interface LLMAnalysisService {

    String generateExplanation(
            CreditApplication application,
            AIAnalysis analysis
    );

}