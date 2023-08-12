package com.example.iaimagemaker.models.chatgpt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatGPTMessage {
    private String role = "system";
    private String content;

    public ChatGPTMessage(String content) {
        this.content = content;
    }
}
