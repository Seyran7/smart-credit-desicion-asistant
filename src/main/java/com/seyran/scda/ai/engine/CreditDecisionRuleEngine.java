package com.seyran.scda.ai.engine;

import com.seyran.scda.ai.rule.CreditRule;
import com.seyran.scda.ai.rule.RuleResult;
import com.seyran.scda.entity.AIAnalysis;
import com.seyran.scda.entity.AIAnalysisDetail;
import com.seyran.scda.entity.CreditApplication;
import com.seyran.scda.enums.AIRecommendation;
import com.seyran.scda.enums.AIStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CreditDecisionRuleEngine {

    private static final int MAX_SCORE = 70;

    private final List<CreditRule> rules;

    public AIAnalysis analyze(CreditApplication application) {

        int totalScore = 0;

        StringBuilder strengths = new StringBuilder();
        StringBuilder risks = new StringBuilder();

        List<AIAnalysisDetail> details = new ArrayList<>();

        for (CreditRule rule : rules) {

            RuleResult result = rule.evaluate(application);

            AIAnalysisDetail detail = AIAnalysisDetail.builder()
                    .ruleName(result.getRuleName())
                    .score(result.getScore())
                    .passed(result.isPassed())
                    .explanation(result.getExplanation())
                    .build();

            details.add(detail);

            totalScore += result.getScore();

            if (result.getStrength() != null && !result.getStrength().isBlank()) {
                strengths.append("✔ ")
                        .append(result.getStrength())
                        .append("\n");
            }

            if (result.getRisk() != null && !result.getRisk().isBlank()) {
                risks.append("⚠ ")
                        .append(result.getRisk())
                        .append("\n");
            }
        }

        AIRecommendation recommendation;

        if (totalScore >= 60) {
            recommendation = AIRecommendation.APPROVE;
        } else if (totalScore >= 35) {
            recommendation = AIRecommendation.REVIEW;
        } else {
            recommendation = AIRecommendation.REJECT;
        }

        String strengthsText =
                strengths.length() == 0
                        ? "Mühüm üstünlük aşkar edilmədi."
                        : strengths.toString();

        String risksText =
                risks.length() == 0
                        ? "Risk aşkar edilmədi."
                        : risks.toString();

        int confidenceScore =
                Math.max(
                        0,
                        Math.min(
                                100,
                                (int) Math.round(((double) totalScore / MAX_SCORE) * 100)
                        )
                );

        int riskScore = 100 - confidenceScore;

        AIAnalysis analysis = AIAnalysis.builder()
                .recommendation(recommendation)
                .aiStatus(AIStatus.COMPLETED)
                .confidenceScore(confidenceScore)
                .riskScore(riskScore)
                .strengths(strengthsText)
                .risks(risksText)
                .build();

        details.forEach(detail -> detail.setAnalysis(analysis));

        analysis.setDetails(details);

        return analysis;
    }
}