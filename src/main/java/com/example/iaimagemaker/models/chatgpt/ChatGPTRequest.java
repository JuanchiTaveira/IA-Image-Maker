package com.example.iaimagemaker.models.chatgpt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatGPTRequest {
    private List<ChatGPTMessage> messages;
    private String model;
    private double temperature;
    @JsonProperty("max_tokens")
    private int maxTokens;
    @JsonProperty("top_p")
    private double topP;
}
