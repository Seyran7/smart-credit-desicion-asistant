package com.seyran.scda.service;

import com.seyran.scda.dto.request.CreditApplicationRequest;
import com.seyran.scda.dto.response.AIAnalysisResponse;
import com.seyran.scda.dto.response.CreditApplicationResponse;
import com.seyran.scda.enums.ApplicationStatus;
import com.seyran.scda.enums.LoanPurpose;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;


public interface CreditApplicationService {
    AIAnalysisResponse getAnalysis(Long id);

    CreditApplicationResponse create(CreditApplicationRequest request);
    CreditApplicationResponse getById(Long id);
    CreditApplicationResponse update(Long id,CreditApplicationRequest request);
    Page<CreditApplicationResponse> getAll(Pageable pageable);
    void delete(Long id);

    Page<CreditApplicationResponse> search(
            String finCode,
            String lastName,
            ApplicationStatus status,
            LoanPurpose purpose,
            BigDecimal minIncome,
            BigDecimal maxIncome,
            BigDecimal minLoan,
            BigDecimal maxLoan,
            Pageable pageable
    );
}
