package com.seyran.scda.ai.client;

import com.seyran.scda.ai.client.dto.GroqRequest;
import com.seyran.scda.ai.client.dto.GroqResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;


@Component
@RequiredArgsConstructor
public class GroqClient {


    private final RestClient restClient;


    @Value("${groq.api.key}")
    private String apiKey;


    private static final String GROQ_URL =
            "https://api.groq.com/openai/v1/chat/completions";


    public String generate(GroqRequest request) {


        GroqResponse response =
                restClient.post()
                        .uri(GROQ_URL)
                        .header(
                                "Authorization",
                                "Bearer " + apiKey
                        )
                        .header(
                                "Content-Type",
                                "application/json"
                        )
                        .body(request)
                        .retrieve()
                        .body(GroqResponse.class);


        if (response == null ||
                response.getChoices() == null ||
                response.getChoices().isEmpty()) {

            throw new RuntimeException(
                    "Groq response is empty"
            );
        }


        return response
                .getChoices()
                .get(0)
                .getMessage()
                .getContent();
    }

}