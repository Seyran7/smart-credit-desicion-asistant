package com.seyran.scda.mapper;

import com.seyran.scda.dto.response.AIAnalysisDetailResponse;
import com.seyran.scda.entity.AIAnalysisDetail;
import org.springframework.stereotype.Component;
@Component
public class AIAnalysisDetailMapper {

    public AIAnalysisDetailResponse toResponse(
            AIAnalysisDetail entity){

        return AIAnalysisDetailResponse.builder()
                .ruleName(entity.getRuleName())
                .score(entity.getScore())
                .passed(entity.getPassed())
                .explanation(entity.getExplanation())
                .build();
    }

}