package com.example.iaimagemaker.models.chatgpt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatGPTResponse {
    private String id;
    private String object;
    private List<ChatGPTChoice> choices;
}
