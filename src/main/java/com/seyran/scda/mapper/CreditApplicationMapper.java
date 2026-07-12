package com.seyran.scda.mapper;

import com.seyran.scda.dto.request.CreditApplicationRequest;
import com.seyran.scda.dto.response.CreditApplicationResponse;
import com.seyran.scda.entity.CreditApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreditApplicationMapper {
    private final AIAnalysisMapper aiAnalysisMapper;
    public CreditApplication toEntity(CreditApplicationRequest request){
        return CreditApplication.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .finCode(request.getFinCode())
                .monthlyIncome(request.getMonthlyIncome())
                .employmentMonths(request.getEmploymentMonths())
                .existingMonthlyDebt(request.getExistingMonthlyDebt())
                .requestedLoanAmount(request.getRequestedLoanAmount())
                .loanTermMonths(request.getLoanTermMonths())
                .purpose(request.getPurpose())
                .build();
    }

    public CreditApplicationResponse toResponse(CreditApplication entity){
        return CreditApplicationResponse.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .finCode(entity.getFinCode())
                .monthlyIncome(entity.getMonthlyIncome())
                .employmentMonths(entity.getEmploymentMonths())
                .existingMonthlyDebt(entity.getExistingMonthlyDebt())
                .requestedLoanAmount(entity.getRequestedLoanAmount())
                .loanTermMonths(entity.getLoanTermMonths())
                .purpose(entity.getPurpose())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .aiAnalysis(
                        aiAnalysisMapper.toResponse(entity.getAiAnalysis())
                )
                .build();
    }
    public void updateEntity(CreditApplicationRequest request, CreditApplication entity){
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setFinCode(request.getFinCode());
        entity.setMonthlyIncome(request.getMonthlyIncome());
        entity.setEmploymentMonths(request.getEmploymentMonths());
        entity.setExistingMonthlyDebt(request.getExistingMonthlyDebt());
        entity.setRequestedLoanAmount(request.getRequestedLoanAmount());
        entity.setLoanTermMonths(request.getLoanTermMonths());
        entity.setPurpose(request.getPurpose());
    }
}
