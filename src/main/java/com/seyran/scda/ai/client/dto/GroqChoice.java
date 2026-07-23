package com.seyran.scda.ai.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroqChoice {

    private int index;

    private GroqMessage message;

}