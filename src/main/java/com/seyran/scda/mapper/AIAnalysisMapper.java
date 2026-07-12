package com.seyran.scda.mapper;

import com.seyran.scda.dto.response.AIAnalysisResponse;
import com.seyran.scda.entity.AIAnalysis;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AIAnalysisMapper {

    private final AIAnalysisDetailMapper detailMapper;

    public AIAnalysisResponse toResponse(AIAnalysis entity) {

        if (entity == null) {
            return null;
        }

        return AIAnalysisResponse.builder()
                .recommendation(entity.getRecommendation())
                .aiStatus(entity.getAiStatus())
                .confidenceScore(entity.getConfidenceScore())
                .riskScore(entity.getRiskScore())
                .strengths(entity.getStrengths())
                .risks(entity.getRisks())
                .explanation(entity.getExplanation())
                .analyzedAt(entity.getAnalyzedAt())
                .details(
                        entity.getDetails()
                                .stream()
                                .map(detailMapper::toResponse)
                                .toList()
                )
                .build();
    }
}