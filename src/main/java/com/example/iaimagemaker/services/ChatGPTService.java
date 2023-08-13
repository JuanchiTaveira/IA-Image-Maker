package com.example.iaimagemaker.services;

import com.example.iaimagemaker.models.chatgpt.ChatGPTMessage;
import com.example.iaimagemaker.models.chatgpt.ChatGPTRequest;
import com.example.iaimagemaker.models.chatgpt.ChatGPTResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ChatGPTService {
    private final String chatGPTApiKey;
    private final String chatGPTModel;
    private static final String CHATGPT_URL = "https://api.openai.com/v1/chat/completions";

    public ChatGPTService(@Value("${chatgpt.api.key}") String chatGPTApiKey, @Value("${chatgpt.model}") String chatGPTModel) {
        this.chatGPTApiKey = chatGPTApiKey;
        this.chatGPTModel = chatGPTModel;
    }

    public String generateChatResponse(String prompt) {
        RestTemplate restTemplate = new RestTemplate();

        ChatGPTRequest request = new ChatGPTRequest();
        request.setMessages(List.of(new ChatGPTMessage("Write one tip to upload to my instagram account about " + prompt + ". Do not use #hashtags.")));
        request.setModel(chatGPTModel);
        request.setTemperature(1.5);
        request.setMaxTokens(150);
        request.setTopP(1);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + chatGPTApiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ChatGPTRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<ChatGPTResponse> response = restTemplate.exchange(
                CHATGPT_URL,
                HttpMethod.POST,
                entity,
                ChatGPTResponse.class
        );

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return response.getBody().getChoices().get(0).getMessage().getContent();
        } else {
            return "Error getting response from ChatGPT.";
        }
    }
}

