package com.seyran.scda.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AIAnalysisDetailResponse {

    private String ruleName;

    private Integer score;

    private Boolean passed;

    private String explanation;

}