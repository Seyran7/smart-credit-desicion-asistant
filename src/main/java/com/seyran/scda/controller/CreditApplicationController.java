package com.seyran.scda.controller;


import com.seyran.scda.dto.request.CreditApplicationRequest;
import com.seyran.scda.dto.response.AIAnalysisResponse;
import com.seyran.scda.dto.response.CreditApplicationResponse;
import com.seyran.scda.exception.InvalidSortDirectionException;
import com.seyran.scda.exception.InvalidSortFieldException;
import com.seyran.scda.service.CreditApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import com.seyran.scda.enums.ApplicationStatus;
import com.seyran.scda.enums.LoanPurpose;

import static com.seyran.scda.util.SortConstants.ALLOWED_SORT_FIELDS;

@RestController
@RequestMapping("/api/v1/credit-applications")
@RequiredArgsConstructor
@Tag(name = "Credit Application API",description = "Operation related to credit applications")
@Validated
public class CreditApplicationController {
    private final CreditApplicationService creditApplicationService;

    @Operation(summary = "Create new credit application")
    @PostMapping
    public ResponseEntity<CreditApplicationResponse> create(@Valid @RequestBody CreditApplicationRequest request) {
        CreditApplicationResponse response = creditApplicationService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Get all applications")
    @GetMapping
    public ResponseEntity<Page<CreditApplicationResponse>> getAll(
            @RequestParam(defaultValue = "0")
            @Min(value = 0, message = "Page cannot be negative")
            int page,
            @RequestParam(defaultValue = "10")
            @Min(value = 1, message = "Size must be at least 1")
            @Max(value = 100, message = "Maximum page size is 100")
            int size,
            @RequestParam(defaultValue = "id")
            String sortBy,
            @RequestParam(defaultValue = "asc")
            String direction
    ) {

        if (!ALLOWED_SORT_FIELDS.contains(sortBy)) {
            throw new InvalidSortFieldException(sortBy);
        }

        Sort.Direction sortDirection;

        try {
            sortDirection = Sort.Direction.fromString(direction);
        } catch (IllegalArgumentException e) {
            throw new InvalidSortDirectionException(direction);
        }
        Sort sort = Sort.by(sortDirection, sortBy);

        Pageable pageable = PageRequest.of(page, size, sort);

        return ResponseEntity.ok(creditApplicationService.getAll(pageable));
    }

    @Operation(summary = "Get application by id")
    @GetMapping("/{id}")
    public ResponseEntity<CreditApplicationResponse> getById(@PathVariable  Long id) {
        return ResponseEntity.ok(creditApplicationService.getById(id));
    }

    @Operation(summary = "Update application")
    @PutMapping("/{id}")
    public ResponseEntity<CreditApplicationResponse> update(@PathVariable Long id,@Valid @RequestBody CreditApplicationRequest request) {
        return ResponseEntity.ok(creditApplicationService.update(id, request));
    }

    @Operation(summary = "Delete application")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        creditApplicationService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "Search credit applications")
    @GetMapping("/search")
    public ResponseEntity<Page<CreditApplicationResponse>> search(

            @RequestParam(required = false) String finCode,

            @RequestParam(required = false) String lastName,

            @RequestParam(required = false) ApplicationStatus status,

            @RequestParam(required = false) LoanPurpose purpose,

            @RequestParam(required = false) BigDecimal minIncome,

            @RequestParam(required = false) BigDecimal maxIncome,

            @RequestParam(required = false) BigDecimal minLoan,

            @RequestParam(required = false) BigDecimal maxLoan,

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "10") int size

    ) {

        Pageable pageable = PageRequest.of(page, size);

        return ResponseEntity.ok(

                creditApplicationService.search(

                        finCode,
                        lastName,
                        status,
                        purpose,
                        minIncome,
                        maxIncome,
                        minLoan,
                        maxLoan,
                        pageable

                )
        );
    }
    @Operation(summary = "Get AI analysis by application id")
    @GetMapping("/{id}/analysis")
    public ResponseEntity<AIAnalysisResponse> getAnalysis(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                creditApplicationService.getAnalysis(id)
        );
    }
}
