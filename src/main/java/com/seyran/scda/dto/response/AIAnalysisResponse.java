package com.seyran.scda.dto.response;

import com.seyran.scda.enums.AIRecommendation;
import com.seyran.scda.enums.AIStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AIAnalysisResponse {

    private AIRecommendation recommendation;

    private AIStatus aiStatus;

    private Integer confidenceScore;

    private String strengths;

    private String risks;

    private String explanation;

    private Integer riskScore;

    private List<AIAnalysisDetailResponse> details;

    private LocalDateTime analyzedAt;

}