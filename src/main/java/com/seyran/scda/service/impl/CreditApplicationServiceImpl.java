package com.seyran.scda.service.impl;

import com.seyran.scda.dto.request.CreditApplicationRequest;
import com.seyran.scda.dto.response.CreditApplicationResponse;
import com.seyran.scda.entity.CreditApplication;
import com.seyran.scda.exception.DuplicateFinCodeException;
import com.seyran.scda.exception.ResourceNotFoundException;
import com.seyran.scda.mapper.CreditApplicationMapper;
import com.seyran.scda.repository.CreditApplicationRepository;
import com.seyran.scda.service.CreditApplicationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditApplicationServiceImpl implements CreditApplicationService {

    private final CreditApplicationRepository repository;
    private final CreditApplicationMapper mapper;
    @Override
    public CreditApplicationResponse create(CreditApplicationRequest request) {
        if (repository.existsByFinCode(request.getFinCode())) {
            throw new DuplicateFinCodeException("Customer with this Fin Code already exists");
        }
        CreditApplication application = mapper.toEntity(request);
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
    public List<CreditApplicationResponse> getAll() {
        return repository.findAll().stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public CreditApplicationResponse getById(Long id) {
        CreditApplication application = repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Credit application not found with id: \" + id"));
        return mapper.toResponse(application);
    }
}
