package com.seyran.scda.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardResponse {

    private Long totalApplications;

    private Long approved;

    private Long review;

    private Long rejected;

    private Double averageConfidence;

    private Double averageRisk;

}