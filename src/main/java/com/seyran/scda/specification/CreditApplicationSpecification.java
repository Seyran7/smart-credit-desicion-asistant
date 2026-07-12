package com.seyran.scda.specification;

import com.seyran.scda.entity.CreditApplication;
import com.seyran.scda.enums.ApplicationStatus;
import com.seyran.scda.enums.LoanPurpose;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class CreditApplicationSpecification {

    private CreditApplicationSpecification() {
    }

    public static Specification<CreditApplication> hasFinCode(String finCode) {

        return (root, query, criteriaBuilder) ->
                finCode == null
                        ? null
                        : criteriaBuilder.equal(root.get("finCode"), finCode);
    }
    public static Specification<CreditApplication> hasStatus(ApplicationStatus status) {

        return (root, query, criteriaBuilder) ->
                status == null
                        ? null
                        : criteriaBuilder.equal(root.get("status"), status);
    }

    public static Specification<CreditApplication> hasPurpose(LoanPurpose purpose) {

        return (root, query, criteriaBuilder) ->
                purpose == null
                        ? null
                        : criteriaBuilder.equal(root.get("purpose"), purpose);
    }

    public static Specification<CreditApplication> hasLastName(String lastName) {

        return (root, query, criteriaBuilder) ->
                lastName == null || lastName.isBlank()
                        ? null
                        : criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("lastName")),
                        "%" + lastName.toLowerCase() + "%"
                );
    }
    public static Specification<CreditApplication> hasMinIncome(BigDecimal minIncome) {

        return (root, query, criteriaBuilder) ->
                minIncome == null
                        ? null
                        : criteriaBuilder.greaterThanOrEqualTo(
                        root.get("monthlyIncome"),
                        minIncome
                );
    }
    public static Specification<CreditApplication> hasMaxIncome(BigDecimal maxIncome) {

        return (root, query, criteriaBuilder) ->
                maxIncome == null
                        ? null
                        : criteriaBuilder.lessThanOrEqualTo(
                        root.get("monthlyIncome"),
                        maxIncome
                );
    }
    public static Specification<CreditApplication> hasMinLoan(BigDecimal minLoan) {

        return (root, query, criteriaBuilder) ->
                minLoan == null
                        ? null
                        : criteriaBuilder.greaterThanOrEqualTo(
                        root.get("requestedLoanAmount"),
                        minLoan
                );
    }
    public static Specification<CreditApplication> hasMaxLoan(BigDecimal maxLoan) {

        return (root, query, criteriaBuilder) ->
                maxLoan == null
                        ? null
                        : criteriaBuilder.lessThanOrEqualTo(
                        root.get("requestedLoanAmount"),
                        maxLoan
                );
    }

}
