package com.seyran.scda.dto.request;

import com.seyran.scda.enums.LoanPurpose;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditApplicationRequest {

    @NotBlank(message = "Ad daxil edilməlidir.")
    @Size(min = 2, max = 50, message = "Ad 2 ilə 50 simvol arasında olmalıdır.")
    private String firstName;

    @NotBlank(message = "Soyad daxil edilməlidir.")
    @Size(min = 2, max = 50, message = "Soyad 2 ilə 50 simvol arasında olmalıdır.")
    private String lastName;

    @NotBlank(message = "FIN kodu daxil edilməlidir.")
    @Size(min = 7, max = 7, message = "FIN kodu dəqiq 7 simvoldan ibarət olmalıdır.")
    private String finCode;

    @NotNull(message = "Aylıq gəlir daxil edilməlidir.")
    @Positive(message = "Aylıq gəlir 0-dan böyük olmalıdır.")
    private BigDecimal monthlyIncome;

    @NotNull(message = "İş stajı daxil edilməlidir.")
    @PositiveOrZero(message = "İş stajı mənfi ola bilməz.")
    private Integer employmentMonths;

    @NotNull(message = "Cari aylıq borc daxil edilməlidir.")
    @PositiveOrZero(message = "Cari aylıq borc mənfi ola bilməz.")
    private BigDecimal existingMonthlyDebt;

    @NotNull(message = "İstənilən kredit məbləği daxil edilməlidir.")
    @Positive(message = "İstənilən kredit məbləği 0-dan böyük olmalıdır.")
    private BigDecimal requestedLoanAmount;

    @NotNull(message = "Kredit müddəti daxil edilməlidir.")
    @Positive(message = "Kredit müddəti 0-dan böyük olmalıdır.")
    private Integer loanTermMonths;

    @NotNull(message = "Kredit məqsədi seçilməlidir.")
    private LoanPurpose purpose;
}