package com.example.iaimagemaker.controllers;

import com.example.iaimagemaker.models.unsplash.UnsplashImage;
import com.example.iaimagemaker.services.ChatGPTService;
import com.example.iaimagemaker.services.ImageTextService;
import com.example.iaimagemaker.services.UnsplashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class ImageController {

    private final ChatGPTService chatGPTService;
    private final UnsplashService unsplashService;
    private final ImageTextService imageTextService;

    @Autowired
    public ImageController(ChatGPTService chatGPTService, UnsplashService unsplashService, ImageTextService imageTextService) {
        this.chatGPTService = chatGPTService;
        this.unsplashService = unsplashService;
        this.imageTextService = imageTextService;
    }

    @PostMapping("/chat")
    public String chatWithGPT(@RequestBody String prompt) {
        return chatGPTService.generateChatResponse(prompt);
    }


    @GetMapping("/search-image")
    public UnsplashImage searchImage(@RequestParam String searchTerm) {
        return unsplashService.getUnsplashImage(searchTerm);
    }

    @GetMapping("/generate-image")
    public byte[] generateImage(@RequestParam String prompt) throws IOException {

        String text = chatGPTService.generateChatResponse(prompt);
        String imageUrl = unsplashService.getUnsplashImage(prompt).getUrls().getFull();

        return imageTextService.generateImageWithText(text, imageUrl);
    }

}
