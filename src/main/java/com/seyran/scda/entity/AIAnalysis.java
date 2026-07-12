package com.seyran.scda.entity;

import com.seyran.scda.enums.AIRecommendation;
import com.seyran.scda.enums.AIStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ai_analysis")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AIAnalysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AIRecommendation recommendation;

    @Column(length = 1000)
    private String risks;

    @Column(length = 1000)
    private String strengths;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AIStatus aiStatus;

    @Column(length = 5000)
    private String explanation;

    private Integer riskScore;

    @OneToMany(
            mappedBy = "analysis",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private List<AIAnalysisDetail> details = new ArrayList<>();


    private Integer confidenceScore;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime analyzedAt;

}
