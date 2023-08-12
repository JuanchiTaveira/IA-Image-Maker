package com.example.iaimagemaker.controllers;

import com.example.iaimagemaker.services.ChatGPTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/image")
public class ImageController {

    private final ChatGPTService chatGPTService;

    @Autowired
    public ImageController(ChatGPTService chatGPTService) {
        this.chatGPTService = chatGPTService;
    }

    @PostMapping("/chat")
    public String chatWithGPT(@RequestBody String prompt) {
        return chatGPTService.generateChatResponse(prompt);
    }
}
