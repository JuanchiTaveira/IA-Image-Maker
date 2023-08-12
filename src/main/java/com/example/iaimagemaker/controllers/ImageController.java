package com.example.iaimagemaker.controllers;

import com.example.iaimagemaker.models.unsplash.UnsplashImage;
import com.example.iaimagemaker.services.ChatGPTService;
import com.example.iaimagemaker.services.UnsplashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/image")
public class ImageController {

    private final ChatGPTService chatGPTService;
    private final UnsplashService unsplashService;

    @Autowired
    public ImageController(ChatGPTService chatGPTService, UnsplashService unsplashService) {
        this.chatGPTService = chatGPTService;
        this.unsplashService = unsplashService;
    }

    @PostMapping("/chat")
    public String chatWithGPT(@RequestBody String prompt) {
        return chatGPTService.generateChatResponse(prompt);
    }


    @GetMapping("/search-image")
    public UnsplashImage searchImage(@RequestParam String searchTerm) {
        return unsplashService.getUnsplashImage(searchTerm);
    }

}
