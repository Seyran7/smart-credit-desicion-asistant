package com.seyran.scda.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ai_analysis_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AIAnalysisDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleName;

    private Integer score;

    private Boolean passed;

    @Column(length = 1000)
    private String explanation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "analysis_id")
    private AIAnalysis analysis;
}