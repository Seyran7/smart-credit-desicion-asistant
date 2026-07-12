package com.seyran.scda.ai.service;

import com.seyran.scda.entity.AIAnalysis;
import com.seyran.scda.entity.CreditApplication;


public interface AIAnalysisService {
    AIAnalysis analyze(CreditApplication application);
}
