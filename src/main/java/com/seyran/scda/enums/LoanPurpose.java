package com.seyran.scda.enums;

public enum LoanPurpose {

    CONSUMER("İstehlak krediti"),
    BUSINESS("Biznes krediti"),
    HOME("Mənzil krediti"),
    AUTO("Avtomobil krediti"),
    EDUCATION("Təhsil krediti"),
    OTHER("Digər");

    private final String displayName;

    LoanPurpose(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
