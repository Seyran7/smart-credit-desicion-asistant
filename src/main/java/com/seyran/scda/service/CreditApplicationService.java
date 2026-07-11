package com.seyran.scda.service;

import com.seyran.scda.dto.request.CreditApplicationRequest;
import com.seyran.scda.dto.response.CreditApplicationResponse;

import java.util.List;

public interface CreditApplicationService {
    CreditApplicationResponse create(CreditApplicationRequest request);
    CreditApplicationResponse getById(Long id);
    CreditApplicationResponse update(Long id,CreditApplicationRequest request);
    List<CreditApplicationResponse> getAll();
    void delete(Long id);
}
