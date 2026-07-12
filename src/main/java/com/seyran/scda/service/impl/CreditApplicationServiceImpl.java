package com.seyran.scda.service.impl;

import com.seyran.scda.ai.service.AIAnalysisService;
import com.seyran.scda.dto.request.CreditApplicationRequest;
import com.seyran.scda.dto.response.CreditApplicationResponse;
import com.seyran.scda.entity.AIAnalysis;
import com.seyran.scda.entity.CreditApplication;
import com.seyran.scda.enums.ApplicationStatus;
import com.seyran.scda.enums.LoanPurpose;
import com.seyran.scda.exception.DuplicateFinCodeException;
import com.seyran.scda.exception.ResourceNotFoundException;
import com.seyran.scda.mapper.AIAnalysisMapper;
import com.seyran.scda.mapper.CreditApplicationMapper;
import com.seyran.scda.repository.CreditApplicationRepository;
import com.seyran.scda.service.CreditApplicationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import static com.seyran.scda.specification.CreditApplicationSpecification.*;
import com.seyran.scda.dto.response.AIAnalysisResponse;

import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
public class CreditApplicationServiceImpl implements CreditApplicationService {

    private final AIAnalysisMapper aiAnalysisMapper;

    private final AIAnalysisService aiAnalysisService;

    private final CreditApplicationRepository repository;
    private final CreditApplicationMapper mapper;
    @Override
    public CreditApplicationResponse create(CreditApplicationRequest request) {

        if (repository.existsByFinCode(request.getFinCode())) {
            throw new DuplicateFinCodeException(
                    "Customer with this Fin Code already exists"
            );
        }

        CreditApplication application = mapper.toEntity(request);

        application.setStatus(ApplicationStatus.NEW);

        AIAnalysis analysis = aiAnalysisService.analyze(application);

        application.setAiAnalysis(analysis);

        CreditApplication savedApplication = repository.save(application);

        return mapper.toResponse(savedApplication);
    }
    @Transactional
    @Override
    public CreditApplicationResponse update(Long id,CreditApplicationRequest request) {
        CreditApplication application = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("CreditApplication with id " + id + " not found"));
        if (!application.getFinCode().equals(request.getFinCode())
                && repository.existsByFinCode(request.getFinCode())) {

            throw new DuplicateFinCodeException(
                    "Customer with this FIN code already exists."
            );
        }
        mapper.updateEntity(request, application);
            CreditApplication updatedApplication = repository.save(application);
            return mapper.toResponse(updatedApplication);

    }

    @Override
    public void delete(Long id) {
        CreditApplication application = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("CreditApplication with id " + id + " not found"));
        repository.delete(application);

    }

    @Override
    public Page<CreditApplicationResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    @Override
    public CreditApplicationResponse getById(Long id) {
        CreditApplication application = repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Credit application not found with id:" + id));
        return mapper.toResponse(application);
    }
    @Override
    public Page<CreditApplicationResponse> search(
            String finCode,
            String lastName,
            ApplicationStatus status,
            LoanPurpose purpose,
            BigDecimal minIncome,
            BigDecimal maxIncome,
            BigDecimal minLoan,
            BigDecimal maxLoan,
            Pageable pageable) {

        Specification<CreditApplication> specification =
                Specification.where(hasFinCode(finCode))
                        .and(hasLastName(lastName))
                        .and(hasStatus(status))
                        .and(hasPurpose(purpose))
                        .and(hasMinIncome(minIncome))
                        .and(hasMaxIncome(maxIncome))
                        .and(hasMinLoan(minLoan))
                        .and(hasMaxLoan(maxLoan));

        return repository.findAll(specification, pageable)
                .map(mapper::toResponse);
    }
    @Override
    public AIAnalysisResponse getAnalysis(Long id) {

        CreditApplication application = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Credit application not found with id: " + id));

        if (application.getAiAnalysis() == null) {
            throw new ResourceNotFoundException(
                    "AI analysis not found for application id: " + id);
        }

        return aiAnalysisMapper.toResponse(application.getAiAnalysis());
    }
}
