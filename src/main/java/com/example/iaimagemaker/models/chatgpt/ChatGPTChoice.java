package com.example.iaimagemaker.models.chatgpt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatGPTChoice {
    private ChatGPTMessage message;

    @JsonProperty("finish_reason")
    private String finishReason;
}
