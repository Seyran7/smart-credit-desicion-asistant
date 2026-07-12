package com.seyran.scda.util;

import java.util.List;

public class SortConstants {

    private SortConstants(){}

    public static final List<String> ALLOWED_SORT_FIELDS = List.of(
            "id",
            "createdAt",
            "monthlyIncome",
            "requestedLoanAmount",
            "lastName",
            "status"
    );
}
