package com.seyran.scda.ai.llm;

import com.seyran.scda.ai.model.LLMResult;
import com.seyran.scda.entity.AIAnalysis;
import com.seyran.scda.entity.CreditApplication;

public interface LLMAnalysisService {

    LLMResult generateExplanation(
            CreditApplication application,
            AIAnalysis analysis
    );
}