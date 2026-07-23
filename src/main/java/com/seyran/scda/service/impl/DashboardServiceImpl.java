package com.seyran.scda.service.impl;

import com.seyran.scda.dto.response.DashboardResponse;
import com.seyran.scda.enums.AIRecommendation;
import com.seyran.scda.repository.CreditApplicationRepository;
import com.seyran.scda.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final CreditApplicationRepository repository;

    @Override
    public DashboardResponse getDashboard() {

        return DashboardResponse.builder()

                .totalApplications(repository.count())
                .approved(
                        repository.countByAiAnalysisRecommendation(
                                AIRecommendation.APPROVE))

                .review(
                        repository.countByAiAnalysisRecommendation(
                                AIRecommendation.REVIEW))

                .rejected(
                        repository.countByAiAnalysisRecommendation(
                                AIRecommendation.REJECT))

                .averageConfidence(
                        repository.averageConfidence())

                .averageRisk(
                        repository.averageRisk())

                .build();
    }
}