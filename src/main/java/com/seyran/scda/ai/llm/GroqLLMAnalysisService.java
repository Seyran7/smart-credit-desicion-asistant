package com.seyran.scda.ai.llm;

import com.seyran.scda.ai.client.GroqClient;
import com.seyran.scda.ai.client.dto.GroqMessage;
import com.seyran.scda.ai.client.dto.GroqRequest;
import com.seyran.scda.ai.model.LLMResult;
import com.seyran.scda.entity.AIAnalysis;
import com.seyran.scda.entity.CreditApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
@RequiredArgsConstructor
public class GroqLLMAnalysisService implements LLMAnalysisService {


    private final GroqClient groqClient;
    @Override
    public LLMResult generateExplanation(
            CreditApplication application,
            AIAnalysis analysis
    ) {

        String prompt = """
Sən Rabitəbank üçün hazırlanmış peşəkar Süni İntellekt Kredit Analitikisən.

Sənin əsas vəzifən kredit müraciətlərini qiymətləndirmək və bank əməkdaşına qısa, aydın və peşəkar izah təqdim etməkdir.

MÜTLƏQ QAYDALAR

1. Bütün cavablarını yalnız Azərbaycan dilində yaz.

2. Heç vaxt ingilis dilindən istifadə etmə.

3. Heç vaxt markdown simvolları (** ### ##) istifadə etmə.

4. Emojidən istifadə etmə.

5. Cavab maksimum 8-10 cümlədən ibarət olsun.

6. Cavab bank əməkdaşı üçün yazılır, müştəri üçün deyil.

7. Java AI nəticəsini əsas qərar kimi qəbul et.
Qərarı dəyişmə.

8. Confidence Score yüksəkdirsə qərar daha inamlı izah edilməlidir.

9. Risk Score yüksəkdirsə risklər daha ətraflı izah edilməlidir.

10. Cavab peşəkar, rəsmi və obyektiv olmalıdır.

11. Heç vaxt məlumat uydurma.

12. Yalnız verilən məlumatlardan istifadə et.

13. Əgər risk yoxdursa bunu açıq şəkildə qeyd et.

14. Əgər gəlir yüksəkdirsə bunu üstünlük kimi vurğula.

15. Əgər iş stajı uzundursa bunu üstünlük kimi qeyd et.

16. Əgər kredit məbləği yüksəkdirsə bunu risk kimi qeyd et.

17. Əgər gəlir aşağıdırsa bunu əsas risk kimi göstər.

18. Kredit məqsədi bank üçün etibarlı hesab olunursa bunu üstünlük kimi qeyd et.

CAVAB FORMATI

Kredit müraciətinin qiymətləndirilməsi

Ümumi qiymətləndirmə

Müsbət cəhətlər

Risklər

Yekun qərar

MÜŞTƏRİ MƏLUMATLARI

Ad:
%s %s

Aylıq gəlir:
%s AZN

İş stajı:
%s ay

Mövcud aylıq borc:
%s AZN

İstənilən kredit:
%s AZN

Kredit müddəti:
%s ay

Kredit məqsədi:
%s

JAVA AI NƏTİCƏLƏRİ

Qərar:
%s

Etibarlılıq:
%s%%

Risk balı:
%s%%

Müsbət cəhətlər:
%s

Risklər:
%s

Bu məlumatlardan istifadə edərək peşəkar bank əməkdaşının oxuyacağı yekun kredit analizi hazırla.
""".formatted(
                application.getFirstName(),
                application.getLastName(),
                application.getMonthlyIncome(),
                application.getEmploymentMonths(),
                application.getExistingMonthlyDebt(),
                application.getRequestedLoanAmount(),
                application.getLoanTermMonths(),
                application.getPurpose().getDisplayName(),
                analysis.getRecommendation(),
                analysis.getConfidenceScore(),
                analysis.getRiskScore(),
                analysis.getStrengths(),
                analysis.getRisks()
        );

        GroqMessage systemMessage = new GroqMessage(
                "system",
                """
                Sən Rabitəbankın daxili AI kredit analitikisən.
        
                Yalnız Azərbaycan dilində cavab ver.
        
                Özünü təqdim etmə.
        
                Salam vermə.
        
                "Mən AI-yam" yazma.
        
                "Xoş gəlmisiniz" yazma.
        
                Yalnız verilən məlumatları analiz et.
        
                Java AI qərarını dəyişmə.
        
                Bank əməkdaşına təqdim ediləcək peşəkar analiz yaz.
                """
        );

        GroqMessage userMessage = new GroqMessage(
                "user",
                prompt
        );

        GroqRequest request =
                new GroqRequest(
                        "llama-3.3-70b-versatile",
                        List.of(systemMessage, userMessage),
                        0.2,
                        700
                );

        String response = groqClient.generate(request);

        return LLMResult.builder()
                .reason(response)
                .recommendation(analysis.getRecommendation())
                .confidenceScore(analysis.getConfidenceScore())
                .riskScore(analysis.getRiskScore())
                .strengths(analysis.getStrengths())
                .weaknesses(analysis.getRisks())
                .advice(
                        "Yekun qərar kredit mütəxəssisi tərəfindən təsdiqlənməlidir."
                )
                .build();
    }

}