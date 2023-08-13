package com.example.iaimagemaker.controllers;

import com.example.iaimagemaker.services.ChatGPTService;
import com.example.iaimagemaker.services.ImageTextService;
import com.example.iaimagemaker.services.UnsplashService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Base64;

@Controller
public class ViewController {

    private final ChatGPTService chatGPTService;
    private final UnsplashService unsplashService;
    private final ImageTextService imageTextService;

    public ViewController(ChatGPTService chatGPTService, UnsplashService unsplashService, ImageTextService imageTextService) {
        this.chatGPTService = chatGPTService;
        this.unsplashService = unsplashService;
        this.imageTextService = imageTextService;
    }

    @GetMapping("/view-image")
    public String viewImage(@RequestParam String prompt, Model model) throws IOException {
        String text = chatGPTService.generateChatResponse(prompt);
        String imageUrl = unsplashService.getUnsplashImage(prompt).getUrls().getSmall();

        byte[] imageWithText = imageTextService.generateImageWithText(text, imageUrl);

        model.addAttribute("imageWithText", Base64.getEncoder().encodeToString(imageWithText));

        return "imageTemplate";
    }

}
