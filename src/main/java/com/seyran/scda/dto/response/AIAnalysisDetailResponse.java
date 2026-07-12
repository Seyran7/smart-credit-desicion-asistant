package com.seyran.scda.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AIAnalysisDetailResponse {

    private String ruleName;

    private Integer score;

    private Boolean passed;

    private String explanation;

}