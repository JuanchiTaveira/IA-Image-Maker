package com.example.iaimagemaker.services;

import com.example.iaimagemaker.models.unsplash.UnsplashImage;
import com.example.iaimagemaker.models.unsplash.UnsplashApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UnsplashService {
    private final String apiUrl = "https://api.unsplash.com/search/photos";
    private final String accessKey;

    public UnsplashService(@Value("${unsplash.api.accesskey}") String accessKey) {
        this.accessKey = accessKey;
    }

    public UnsplashImage getUnsplashImage(String searchTerm) {
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "?query=" + searchTerm + "&client_id=" + accessKey;

        UnsplashApiResponse response = restTemplate.getForObject(url, UnsplashApiResponse.class);

        if (response != null && response.getResults() != null && !response.getResults().isEmpty()) {
            return response.getResults().get(0);
        }

        return null;
    }
}

