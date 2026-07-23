package com.seyran.scda.ai.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroqRequest {


    private String model;


    private List<GroqMessage> messages;


    private double temperature;


    private int max_tokens;

}